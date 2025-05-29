package com.example.contactapp.data.database

import com.example.contactapp.data.enitity.Contact
import kotlinx.coroutines.flow.onEach

class ContactRepository(private val dao: ContactDao) {
    suspend fun upsertContact(contact: Contact){
         dao.upsertContact(contact)
    }
    suspend fun deleteContact(contact: Contact){
        dao.deleteContact(contact = contact)
    }
    fun getAllContactsOrderByName() = dao.getAllContactsOrderByName().onEach {contact ->  }

        fun getAllContactsOrderByDate() = dao.getAllContactsOrderBYDate().onEach {contact ->  }

}