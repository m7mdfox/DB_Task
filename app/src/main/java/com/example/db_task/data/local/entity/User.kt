package com.example.db_task.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [Index(value = ["name"])]
)
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val email:String? = null
)
