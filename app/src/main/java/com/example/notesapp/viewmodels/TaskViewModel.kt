package com.example.notesapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.notesapp.models.Task
import com.example.notesapp.repository.TaskRepository


class TaskViewModel(application: Application):AndroidViewModel(application) {
    private val taskRepository= TaskRepository(application)
    val taskStateFlow get()=taskRepository.taskStateFlow
    val statusLiveData get()=taskRepository.statusLiveData
    val sortByLiveData get()=taskRepository.sortByLiveData
    fun setSortBy(sort:Pair<String,Boolean>){
        taskRepository.setSortBy(sort)
    }
    fun getTaskList(isAsc:Boolean,sortByName:String){
        taskRepository.getTaskList(isAsc,sortByName)
    }
    fun insertTask(task: Task){
        taskRepository.insertTask(task)
    }
    fun deleteTask(task:Task){
        taskRepository.deleteTask(task)
    }
    fun deleteTaskUsingId(taskId:String){
        taskRepository.deleteTaskUsingId(taskId)
    }
    fun updateTask(task: Task){
        taskRepository.updateTask(task)
    }
    fun updateTaskParticuar(taskId: String,title:String,description:String){
        taskRepository.updateTaskPaticularField(taskId,title,description)
    }
    fun searchTaskList(query:String){
        taskRepository.searchTaskList(query)
    }
}