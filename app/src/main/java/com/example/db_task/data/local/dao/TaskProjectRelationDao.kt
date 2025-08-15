package com.example.db_task.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.db_task.data.local.entity.TaskProjectRelation

@Dao
interface TaskProjectRelationDao {

    @Insert
    suspend fun insertTaskProjectRelation(taskProjectRelation: TaskProjectRelation)
}