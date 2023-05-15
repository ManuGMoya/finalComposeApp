package com.manudev.finalcomposeapp.addtasks.ui.model

data class TaskModel(
    var id: Int = System.currentTimeMillis().hashCode(),
    val task: String,
    var selected: Boolean = false
)
