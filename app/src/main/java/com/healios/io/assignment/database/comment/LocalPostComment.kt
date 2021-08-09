package com.healios.io.assignment.database.comment

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_post_comment")
data class LocalPostComment(
    @PrimaryKey(autoGenerate = true)
    val cID: Long = 0L,

    @ColumnInfo(name = "postId")
    val postId: Int?,

    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "email")
    val email: String?,

    @ColumnInfo(name = "body")
    val body: String?
)
