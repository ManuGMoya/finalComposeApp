package com.manudev.finalcomposeapp.addtasks.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manudev.finalcomposeapp.addtasks.domain.AddTaskUseCase
import com.manudev.finalcomposeapp.addtasks.domain.GetTasksUseCase
import com.manudev.finalcomposeapp.addtasks.domain.UpdateTaskUseCase
import com.manudev.finalcomposeapp.addtasks.ui.TasksUiState.Error
import com.manudev.finalcomposeapp.addtasks.ui.TasksUiState.Loading
import com.manudev.finalcomposeapp.addtasks.ui.TasksUiState.Success
import com.manudev.finalcomposeapp.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    getTasksUseCase: GetTasksUseCase
) : ViewModel() {

    val uiState: StateFlow<TasksUiState> = getTasksUseCase().map(::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)


    private val _showDialog = MutableLiveData<Boolean>()
    var showDialog: LiveData<Boolean> = _showDialog

    fun closeDialog() {
        _showDialog.value = false
    }

    fun onTaskCreated(task: String) {
        _showDialog.value = false

        viewModelScope.launch {
            addTaskUseCase.invoke(
                TaskModel(task = task)
            )
        }

    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        viewModelScope.launch {
            updateTaskUseCase(taskModel.copy(selected = !taskModel.selected))
        }
    }

    fun onItemRemove(taskModel: TaskModel) {
        // Borrar Item
        /*        val task = _taskList.find { it.id == taskModel.id }
                _taskList.remove(task)*/
    }

}
