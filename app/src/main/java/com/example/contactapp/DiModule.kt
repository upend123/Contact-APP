package com.example.contactapp

import android.app.Application
import androidx.room.Room
import com.example.contactapp.data.database.ContactDatabase
import com.example.contactapp.data.database.ContactRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DiModule {

    @Provides
    @Singleton
    fun provideContactRepository(database: ContactDatabase): ContactRepository{
        return ContactRepository(database.contactDao())
    }

    @Provides
    @Singleton
    fun provideContactDatabase(application: Application): ContactDatabase{
        return Room.databaseBuilder(
            application,
            ContactDatabase::class.java,
            "contact_database.sql"
        ).build()

    }


}