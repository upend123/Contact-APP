package com.example.contactapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.contactapp.data.enitity.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}