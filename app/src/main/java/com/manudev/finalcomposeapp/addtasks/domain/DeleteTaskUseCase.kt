package com.manudev.finalcomposeapp.addtasks.domain

import com.manudev.finalcomposeapp.addtasks.data.TaskRepository
import com.manudev.finalcomposeapp.addtasks.ui.model.TaskModel
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(task: TaskModel) {
        taskRepository.deleteTask(task)
    }
}
