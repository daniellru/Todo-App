package com.example.todo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo.model.Todo
import com.example.todo.repository.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val app: Application,
    private val repository: Repository): AndroidViewModel(app) {

        val todoList: LiveData<List<Todo>> = repository.getTodoList()

        fun insertTodo(todo: Todo){
            viewModelScope.launch {
                repository.insertTodo(todo)
            }
        }

    fun updateTodo(todo:Todo){
        viewModelScope.launch {
            repository.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo){
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }


}