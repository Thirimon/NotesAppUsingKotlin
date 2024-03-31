package com.example.notesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notesapp.converters.TypeConverter
import com.example.notesapp.dao.TaskDao
import com.example.notesapp.models.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class TaskDatabase:RoomDatabase() {
    abstract val taskDao:TaskDao
    companion object{
        private var INSTANCE: TaskDatabase?=null
        fun getInstance(context: Context):TaskDatabase{
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_db"
                ).build().also {
                    INSTANCE=it
                }
            }
        }
    }

}