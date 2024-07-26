package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.todo.database.TodoDatabase
import com.example.todo.repository.Repository
import com.example.todo.viewmodel.MainViewModel
import com.example.todo.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewModel()
    }

    private fun setUpViewModel() {
        val todoRepository = Repository(TodoDatabase(this))
        val viewModelFactory = MainViewModelFactory(application, todoRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

    }

}