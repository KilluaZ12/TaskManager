package com.example.taskmanager.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    var title: String? = null,
    var description: String? = null
) : Serializable
