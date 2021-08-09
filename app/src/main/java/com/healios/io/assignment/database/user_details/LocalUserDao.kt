package com.healios.io.assignment.database.user_details

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocalUserDao {

    @Query("SELECT * FROM local_user_details ")
    fun getAllUUserDetails(): LiveData<List<LocalUserDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUserDetails(localPost: List<LocalUserDetails>)


    @Query("SELECT * FROM local_user_details WHERE id=:userId")
    fun getSelectedUser(userId: Int): LocalUserDetails
}