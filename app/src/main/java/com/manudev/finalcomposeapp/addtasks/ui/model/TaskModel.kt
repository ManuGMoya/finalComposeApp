package com.manudev.finalcomposeapp.addtasks.ui.model

data class TaskModel(
    var id: Long = System.currentTimeMillis(),
    val task: String,
    var selected: Boolean = false
)
