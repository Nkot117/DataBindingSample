package com.example.sampledatabinding


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map

data class Task(val name: String)

class MainViewModel : ViewModel() {
    private val _tasks: MutableLiveData<List<Task>> = MutableLiveData()
    val tasks: LiveData<List<Task>> = _tasks
    val taskCount: LiveData<Int> = _tasks.map { it?.size ?: 0 }
    val taskName: MutableLiveData<String> = MutableLiveData<String>()
    init {
        _tasks.value = listOf(
            Task("Task 1"),
            Task("Task 2"),
            Task("Task 3")
        )
    }

    fun addTask() {
        val taskList = _tasks.value?.toMutableList() ?: mutableListOf()
        taskName.value?.let {
            taskList.add(Task(it))
            _tasks.value = taskList
            taskName.value = ""
        }
    }
}