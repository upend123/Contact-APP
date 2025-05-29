package com.example.contactapp.data.presantation.screens.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashScreenViewModel: ViewModel(){

     var isLoadingFinished by mutableStateOf(false)
         internal set
    init {
       viewModelScope.launch {
           delay(3000)
           isLoadingFinished = true
       }
    }

}