package com.healios.io.assignment.di

import android.content.Context
import androidx.room.Room
import com.healios.io.assignment.database.AppDatabase
import com.healios.io.assignment.database.comment.LocalPostCommentDao
import com.healios.io.assignment.database.posts.LocalPostDao
import com.healios.io.assignment.database.user_details.LocalUserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "App_data").build()
    }

    @Provides
    fun provideLocalPostDao(appDatabas: AppDatabase): LocalPostDao {
        return appDatabas.localPostDao
    }

    @Provides
    fun provideLocalPostComment(appDatabas: AppDatabase): LocalPostCommentDao {
        return appDatabas.localPostComment
    }

    @Provides
    fun provideLocalUserDetails(appDatabas: AppDatabase): LocalUserDao {
        return appDatabas.localUserDetails
    }
}