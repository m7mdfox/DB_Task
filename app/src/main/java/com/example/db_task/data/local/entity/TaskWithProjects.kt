package com.example.db_task.data.local.entity

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class TaskWithProjects(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = TaskProjectRelation::class,
            parentColumn = "taskId",
            entityColumn = "projectId"
        )
    )
    val projects: List<Project>
)
