package com.example.db_task.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.db_task.data.local.entity.ProjectWithTasks

@Dao
interface ProjectWithTasksDao {

    @Transaction
    @Query("SELECT * FROM projects WHERE id = :projectId")
    suspend fun getProjectWithTasks(projectId: Int): ProjectWithTasks?


}