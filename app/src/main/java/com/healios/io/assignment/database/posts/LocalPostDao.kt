package com.healios.io.assignment.database.posts

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface LocalPostDao {

    @Query("SELECT * FROM LOCAL_POST_TABLE ")
    fun getAllLocalPost(): LiveData<List<LocalPost>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllPosts(localPost: List<LocalPost>)

    @Query("SELECT * FROM local_post_table WHERE UserId=:userId")
    fun getUserSelectedPost(userId: Int): List<LocalPost>
}