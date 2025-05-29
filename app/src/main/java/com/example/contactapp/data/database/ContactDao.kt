package com.example.contactapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.contactapp.data.enitity.Contact
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM `contact_table` ORDER BY `user_name` ASC")
    fun getAllContactsOrderByName() : Flow<List<Contact>>

    @Query("SELECT * FROM `contact_table` ORDER BY `date` DESC")
    fun getAllContactsOrderBYDate() : Flow<List<Contact>>
}
