package com.manudev.finalcomposeapp.addtasks.data.di

import android.content.Context
import androidx.room.Room
import com.manudev.finalcomposeapp.addtasks.data.TaskDao
import com.manudev.finalcomposeapp.addtasks.data.TasksDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    fun provideTaskDao(tasksDataBase: TasksDataBase): TaskDao {
        return tasksDataBase.taskDao()
    }

    @Provides
    @Singleton
    fun provideTasksDataBase(@ApplicationContext appContext: Context): TasksDataBase {
        return Room.databaseBuilder(appContext, TasksDataBase::class.java, "TasksDataBase").build()
    }
}