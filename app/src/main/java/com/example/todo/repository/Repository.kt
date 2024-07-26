package com.example.todo.repository

import com.example.todo.database.TodoDatabase
import com.example.todo.model.Todo

class Repository( private val database: TodoDatabase) {

    suspend fun insertTodo(todo: Todo) = database.getDao().insertTodo(todo)
    suspend fun deleteTodo(todo: Todo) = database.getDao().deleteTodo(todo)
    suspend fun updateTodo(todo: Todo) = database.getDao().updateTodo(todo)

    fun getTodoList() = database.getDao().getAllTodos()


}