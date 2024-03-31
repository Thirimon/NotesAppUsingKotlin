package com.example.notesapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notesapp.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query(""" Select * from Task order by 
        case when :isAsc=1 THEN taskTitle END ASC,
        case when :isAsc=0 THEN taskTitle End DESC""")
    fun getTaskListSortByTaskTitle(isAsc:Boolean): Flow<List<Task>>
    @Query(""" Select * from Task order by 
        case when :isAsc=1 THEN date END ASC,
        case when :isAsc=0 THEN date End DESC""")
    fun getTaskListSortByTaskDate(isAsc: Boolean):Flow<List<Task>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task):Long
    @Delete
    suspend fun deleteTask(task: Task):Int
    @Query("Delete from task where taskId ==:taskId")
    suspend fun deleteTaskUsingId(taskId:String):Int
    @Update
    suspend fun updateTask(task: Task):Int
    @Query("Update task set taskTitle=:title,description =:description where taskId=:taskId")
    suspend fun updateTaskPaticularField(taskId: String,title:String,description:String):Int
    @Query("select * from Task where taskTitle LIKE :query order by date desc")
    fun searchTaskList(query:String):Flow<List<Task>>
}