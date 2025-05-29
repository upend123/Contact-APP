package com.example.contactapp.data.database

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactapp.data.enitity.Contact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class ContactViewModel @Inject constructor(
    val  repository: ContactRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<AppState>(AppState())
  private var isSortedByName = MutableStateFlow(true)

    private val getAllContact = isSortedByName.flatMapLatest {
        if (it) {
            repository.getAllContactsOrderByName()
        } else {
            repository.getAllContactsOrderByDate()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList<Contact>())

    var state =
        combine(_uiState, getAllContact, isSortedByName) { currentState, contacts, isSortedByName ->
            currentState.copy(dataOfContacts = contacts)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), AppState())

    fun SortByName(){
        isSortedByName.value = true
    }
    fun SortByDate(){
        isSortedByName.value = false
    }


    fun upsertContact() {
        viewModelScope.launch {
            val contact = Contact(
                id = state.value.id.value,
                name = state.value.name.value,
                email = state.value.email.value,
                phoneNumber = state.value.phoneNumber.value,
                dateOfCreation = System.currentTimeMillis(),
                image = state.value.image.value,
                isActive = true

            )
            repository.upsertContact(contact)

            _uiState.value = _uiState.value.copy(
                id = mutableIntStateOf(0), name = mutableStateOf(""), email = mutableStateOf(""),
                phoneNumber = mutableStateOf(""), isActive = mutableStateOf(false),
                image = mutableStateOf(null), dateOfCreation = mutableLongStateOf(0)
            )
            isSortedByName.value = isSortedByName.value
        }

    }

    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            repository.deleteContact(contact)
        }
    }

}


data class AppState(

    var loading: Boolean = false,
    var dataOfContacts: List<Contact> = emptyList<Contact>(),
    var onError: String = "",
    var id: MutableState<Int> = mutableIntStateOf(0),
    var name: MutableState<String> = mutableStateOf(""),
    var email: MutableState<String> = mutableStateOf(""),
    var phoneNumber: MutableState<String> = mutableStateOf(""),
    var isActive: MutableState<Boolean> = mutableStateOf(false),
    var dateOfCreation: MutableState<Long> = mutableLongStateOf(0),
    var image: MutableState<ByteArray?> = mutableStateOf(null),
//var isChangingOrder : MutableState<Boolean> = mutableStateOf(false)


)