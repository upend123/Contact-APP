package com.example.contactapp.data.enitity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "user_name")
    var name : String,
    var email: String,
    var phoneNumber: String,
    var isActive : Boolean,
    @ColumnInfo(name = "date")
    var dateOfCreation: Long,

    var image : ByteArray? = null

) {
}