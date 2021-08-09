package com.healios.io.assignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {

        private const val APP_DATABASE_NAME = "Healio_Io"
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                //Check if DB already exists
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        APP_DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }

}