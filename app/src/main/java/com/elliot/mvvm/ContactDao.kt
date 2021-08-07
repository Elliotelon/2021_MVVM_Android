package com.elliot.mvvm

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface ContactDao {

    @Query("Select * From contact ORDER BY name ASC")
    fun getAll() : LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun insert(contact : Contact)

    @Delete
    fun delete(contact: Contact)

}