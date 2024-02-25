package com.example.sampledatabinding


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
class MainViewModel : ViewModel() {
    private val _tasks: MutableLiveData<List<Task>> = MutableLiveData()
    val tasks: LiveData<List<Task>> = _tasks
    val taskCount: LiveData<Int> = _tasks.map { it?.size ?: 0 }
    val taskName: MutableLiveData<String> = MutableLiveData<String>()

    fun addTask() {
        val taskList = _tasks.value?.toMutableList() ?: mutableListOf()
        taskName.value?.let {
            taskList.add(Task(it))
            _tasks.value = taskList
            taskName.value = ""
        }
    }
}