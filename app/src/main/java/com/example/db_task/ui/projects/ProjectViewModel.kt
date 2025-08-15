package com.example.db_task.ui.projects

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.db_task.MainActivity
import com.example.db_task.data.local.dao.ProjectDao
import com.example.db_task.data.local.database.AppDatabase
import com.example.db_task.data.local.entity.Project
import com.example.db_task.data.local.entity.Task
import kotlinx.coroutines.flow.Flow

class ProjectViewModel() : ViewModel() {

    private val projectDao = AppDatabase.db!!.projectDao()

    val allProjects: LiveData<List<Project>> = projectDao.getAllProjects()

    fun getTasksForProject(projectId: Int): Flow<List<Task>> {
        return projectDao.getTasksInProjectFlow(projectId)
    }
}