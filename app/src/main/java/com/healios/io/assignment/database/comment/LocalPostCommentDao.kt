package com.healios.io.assignment.database.comment

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocalPostCommentDao {

    @Query("SELECT * FROM local_post_comment ")
    fun getAllLocalPostComment(): LiveData<List<LocalPostComment>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllPostsComment(localPost: List<LocalPostComment>)

    @Query("SELECT * FROM local_post_comment WHERE PostID=:postID")
    fun getSelectedPostComment(postID: Int): List<LocalPostComment>
}