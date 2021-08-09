package com.healios.io.assignment.database.posts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_post_table")
data class LocalPost(
    @PrimaryKey(autoGenerate = true)
    val uId: Long = 0L,


    @ColumnInfo(name = "UserId")
    val UserId: Int?,

    @ColumnInfo(name = "Id")
    val Id: Int?,

    @ColumnInfo(name = "Title")
    val Title: String?,

    @ColumnInfo(name = "Body")
    val Body: String?,


    )
