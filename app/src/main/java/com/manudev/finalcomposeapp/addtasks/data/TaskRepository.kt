package com.manudev.finalcomposeapp.addtasks.data

import com.manudev.finalcomposeapp.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    val tasks: Flow<List<TaskModel>> = taskDao.getTasks().map { items ->
        items.map {
            TaskModel(
                it.id,
                it.task,
                it.selected
            )
        }
    }



    suspend fun addTask(taskModel: TaskModel) {
        taskDao.addTask(TaskEntity(taskModel.id, taskModel.task))
    }

    suspend fun updateTask(task: TaskModel) {
        taskDao.updateTask(TaskEntity(task.id, task.task, task.selected))
    }
}