package com.example.db_task.data.local.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.db_task.data.local.dao.AttachmentDao
import com.example.db_task.data.local.dao.ProjectDao
import com.example.db_task.data.local.dao.ProjectWithTasksDao
import com.example.db_task.data.local.dao.TaskDao
import com.example.db_task.data.local.dao.UserDao
import com.example.db_task.data.local.entity.Attachment
import com.example.db_task.data.local.entity.Project
import com.example.db_task.data.local.entity.ProjectWithTasks
import com.example.db_task.data.local.entity.Task
import com.example.db_task.data.local.entity.User
import java.util.concurrent.Executors
import androidx.room.TypeConverters
import com.example.db_task.data.local.converter.Converters
import com.example.db_task.data.local.dao.TaskProjectRelationDao
import com.example.db_task.data.local.dao.TaskWithProjectsDao
import com.example.db_task.data.local.entity.TaskProjectRelation
import com.example.db_task.data.local.entity.TaskWithProjects


@Database(
    entities = [
        Project::class,
        Task::class,
        User::class,
        Attachment::class,
        TaskProjectRelation::class,
        ], version = 4,)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun projectDao(): ProjectDao
    abstract fun taskDao(): TaskDao
    abstract fun userDao(): UserDao
    abstract fun attachmentDao(): AttachmentDao
    abstract fun projectWithTasksDao(): ProjectWithTasksDao
    abstract fun taskProjectRelationDao(): TaskProjectRelationDao
    abstract fun taskWithProjectsDao(): TaskWithProjectsDao


    companion object {
        private var _db: AppDatabase? = null
        val db get() = _db
        private const val DB_NAME = "myDB"

        fun createDataBase(context: Context): AppDatabase{
            if (_db == null){
                _db = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration(dropAllTables = true)
                    .build()
            }
            return _db!!
        }
    }
}
