package com.example.todo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.MainActivity
import com.example.todo.R
import com.example.todo.adapter.TodoAdapter
import com.example.todo.databinding.FragmentHomeBinding
import com.example.todo.model.Todo
import com.example.todo.viewmodel.MainViewModel


class HomeFragment : Fragment(), TodoAdapter.OnItemClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var todoAdapter: TodoAdapter

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ((activity) as MainActivity).viewModel

        setupRecyclerView()

        binding.floatingHome.setOnClickListener{
            it.findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
    }

    private fun setupRecyclerView() {
        todoAdapter = TodoAdapter(this)
        binding.rvTodo.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = todoAdapter
        }

        viewModel.todoList.observe(viewLifecycleOwner){
            todoAdapter.setTodoList(it)
        }


    }

    override fun onDeleteClick(todo: Todo) {
        viewModel.deleteTodo(todo)
    }

    override fun onItemClicked(todo: Todo) {
        val action = HomeFragmentDirections.actionHomeFragmentToEditFragment(todo)
        findNavController().navigate(action)
    }


}