package com.example.db_task

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.db_task.data.local.dao.AttachmentDao
import com.example.db_task.data.local.database.AppDatabase
import com.example.db_task.data.local.entity.Attachment
import com.example.db_task.data.local.entity.Project
import com.example.db_task.data.local.entity.Task
import com.example.db_task.data.local.entity.TaskProjectRelation
import com.example.db_task.data.local.entity.User
import com.example.db_task.ui.theme.DB_TaskTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import kotlin.io.path.Path

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppDatabase.createDataBase(context = applicationContext)

        lifecycleScope.launch {

        }

    }
}

suspend fun SuspendDAOvsFlowDAO(){
    susProjects()
    withTimeout(500) {
        AppDatabase.db?.projectDao()?.getAllProjectsFlow()?.collect { projects ->
            Log.d("DB_TEST", "Flow emission: ${projects.toString()}")
        }
    }
}

suspend fun testDataBase() {
    val DB = AppDatabase.db

    DB?.userDao()?.addUser(User(1, "Mohamed Mostafa"))
    DB?.projectDao()?.addProject(Project(2001, "Testing Project", 1))
    DB?.taskDao()?.addTask(Task(1001, "Testing task", 2001))
    DB?.attachmentDao()?.addAttachment(
        Attachment(
            1,
            "C://Users/fcbmo/Pictures/WhatsApp Image 2025-01-12 at 20.50.15_f7184aa3.jpg/",
            1001
        )
    )

    val insertedUser = DB?.userDao()?.getById(1)
    val insertedProject = DB?.projectDao()?.getById(2001)
    val insertedTask = DB?.taskDao()?.getById(1001)
    val insertedAttachment = DB?.attachmentDao()?.getById(1)
    DB?.taskProjectRelationDao()?.insertTaskProjectRelation(TaskProjectRelation(2001, 1001))
    Log.d("DB_TEST", "Inserted User: $insertedUser")
    Log.d("DB_TEST", "Inserted Project: $insertedProject")
    Log.d("DB_TEST", "Inserted Task: $insertedTask")
    Log.d("DB_TEST", "Inserted Attachment: $insertedAttachment")


}

suspend fun getName() {
    val name = AppDatabase.db?.userDao()?.getById(1)?.name
    Log.d("Testing_Query", "Name  is $name")

}

suspend fun getProjectWithTasks() {
    val project = AppDatabase.db?.projectWithTasksDao()?.getProjectWithTasks(2001)
    Log.d("DB_TEST", "Project with Tasks : $project")
}

suspend fun susProjects() {
    val projects = AppDatabase.db?.projectDao()?.getAllProjectsOnce()
    projects?.forEach { project -> Log.d("DB_TEST",project.toString()) }
}
