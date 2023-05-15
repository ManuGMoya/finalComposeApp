package com.manudev.finalcomposeapp.addtasks.ui

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manudev.finalcomposeapp.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor() : ViewModel() {


    private val _showDialog = MutableLiveData<Boolean>()
    var showDialog: LiveData<Boolean> = _showDialog

    private val _taskList = mutableStateListOf<TaskModel>()
    var taskList: List<TaskModel> = _taskList

    fun closeDialog() {
        _showDialog.value = false
    }

    fun onTaskCreated(task: String) {
        _showDialog.value = false
        _taskList.add(TaskModel(task = task))
        Log.i("ManuDev", task)

    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        val index = _taskList.indexOf(taskModel)
        _taskList[index] = _taskList[index].let {
            it.copy(selected = !it.selected)
        }
    }

    fun onItemRemove(taskModel: TaskModel) {
        val task = _taskList.find { it.id == taskModel.id }
        _taskList.remove(task)
    }

}
