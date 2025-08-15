package com.example.db_task.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Transaction
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.db_task.data.local.entity.Project
import com.example.db_task.data.local.entity.ProjectWithTasks
import com.example.db_task.data.local.entity.Task
import com.example.db_task.data.local.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProject(project: Project)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProjects(projects: List<Project>)

    @Query("SELECT * FROM projects WHERE id = :id")
    suspend fun getById(id : Int): Project

    @Query("SELECT * FROM projects")
    suspend fun getAllProjectsOnce(): List<Project>

    @Query("SELECT * FROM projects")
    fun getAllProjectsFlow(): Flow<Project>

    @Query("SELECT * FROM tasks WHERE projectId = :projectId")
    fun getTasksInProjectFlow(projectId: Int): Flow<List<Task>>

    @Query("SELECT * FROM projects")
    fun getAllProjects(): LiveData<List<Project>>

    @Query("""
        SELECT p.* FROM projects p
        JOIN tasks t ON p.id = t.projectId
        GROUP BY p.id
        HAVING COUNT(t.id) > 3
    """)
    fun getProjectsWithMoreThanThreeTasks(): List<Project>

    @RawQuery
    fun getProjectsWithRawQuery(query: SupportSQLiteQuery): List<Project>


}