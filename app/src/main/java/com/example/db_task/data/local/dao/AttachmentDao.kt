package com.example.db_task.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.db_task.data.local.entity.Attachment
import com.example.db_task.data.local.entity.User

@Dao
interface AttachmentDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAttachment(attachment: Attachment)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAttachments(attachments: List<Attachment>)

    @Query("SELECT * FROM attachments WHERE id = :id")
    suspend fun getById(id : Int): Attachment
}