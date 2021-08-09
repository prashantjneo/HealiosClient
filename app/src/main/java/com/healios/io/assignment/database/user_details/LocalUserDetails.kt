package com.healios.io.assignment.database.user_details

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "local_user_details")
data class LocalUserDetails(
    @PrimaryKey(autoGenerate = true)
    val uDetailsId: Long = 0L,


    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "username")
    val username: String?,



    @ColumnInfo(name = "email")
    val email: String?,

    @ColumnInfo(name = "phone")
    val phone: String?,


    @ColumnInfo(name = "website")
    val website: String?,

    )
