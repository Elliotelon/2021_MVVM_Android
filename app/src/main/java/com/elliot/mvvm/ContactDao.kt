package com.elliot.mvvm

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface ContactDao {

    @Query("Select * From contact ORDER BY name ASC")
    fun getAll() : LiveData<List<Contact>>

    @Insert(onConflict = REPLACE)
    fun insert(contact : Contact)

    @Delete
    fun delete(contact: Contact)




}