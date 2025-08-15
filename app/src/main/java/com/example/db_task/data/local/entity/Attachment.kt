package com.example.db_task.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
@Entity(
    tableName = "attachments",
    indices = [Index(value = ["taskId"])]
)
data class Attachment(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val filePath: String,
    val taskId:  Int
)
