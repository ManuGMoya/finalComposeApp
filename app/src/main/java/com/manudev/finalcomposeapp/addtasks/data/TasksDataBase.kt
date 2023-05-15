package com.manudev.finalcomposeapp.addtasks.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskEntity::class], version = 1)
abstract class TasksDataBase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

}
