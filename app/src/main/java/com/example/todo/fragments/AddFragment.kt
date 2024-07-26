package com.example.todo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.todo.MainActivity
import com.example.todo.R
import com.example.todo.databinding.FragmentAddBinding
import com.example.todo.model.Todo
import com.example.todo.viewmodel.MainViewModel

class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ((activity) as MainActivity).viewModel

        binding.floatingAddTodo.setOnClickListener {
            addNewTodo()
            requireActivity().supportFragmentManager.popBackStack()
        }

    }

    private fun addNewTodo() {
        if(binding.edTitle.text.isNotEmpty() && binding.edDescription.text.isNotEmpty()){
            viewModel.insertTodo(Todo(0,
                binding.edTitle.text.toString(),
                binding.edDescription.text.toString()))
        }else{
            Toast.makeText(context, "FILL ALL THE FIELDS UP", Toast.LENGTH_SHORT).show()
        }
    }


}