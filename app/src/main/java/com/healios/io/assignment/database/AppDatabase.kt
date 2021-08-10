package com.healios.io.assignment.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.healios.io.assignment.database.comment.LocalPostComment
import com.healios.io.assignment.database.comment.LocalPostCommentDao
import com.healios.io.assignment.database.posts.LocalPost
import com.healios.io.assignment.database.posts.LocalPostDao
import com.healios.io.assignment.database.user_details.LocalUserDao
import com.healios.io.assignment.database.user_details.LocalUserDetails


@Database(entities = [LocalPost::class, LocalPostComment::class,LocalUserDetails::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val localPostDao: LocalPostDao
    abstract val localPostComment: LocalPostCommentDao
    abstract val localUserDetails: LocalUserDao

}