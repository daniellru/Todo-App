package com.example.todo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.todo.MainActivity
import com.example.todo.R
import com.example.todo.databinding.FragmentEditBinding
import com.example.todo.model.Todo
import com.example.todo.viewmodel.MainViewModel


class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding
    private lateinit var viewModel: MainViewModel

    private val args: EditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ((activity) as MainActivity).viewModel

        val todo = args.todo

        setUpEditView(todo)

        binding.floatingEdit.setOnClickListener {
            onEditClicked(todo)
        }


    }

    private fun onEditClicked(todo: Todo) {
        if(binding.edTitle.text.toString() != todo.title || binding.edDesc.text.toString() != todo.desc ){
            viewModel.updateTodo(Todo(todo.id,binding.edTitle.text.toString(), binding.edDesc.text.toString()))
            activity?.supportFragmentManager?.popBackStack()
        }else{
            Toast.makeText(context, "NOTHING HAS BEEN CHANGED", Toast.LENGTH_SHORT).show()
        }
    }



    private fun setUpEditView(todo: Todo) {
        binding.edTitle.setText(todo.title)
        binding.edDesc.setText(todo.desc)
    }


}