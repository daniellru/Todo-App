package com.example.todo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.TodoItemBinding
import com.example.todo.model.Todo
import com.example.todo.viewmodel.MainViewModel

class TodoAdapter(private val listener: OnItemClickListener): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var todoList = emptyList<Todo>()

    interface OnItemClickListener{
        fun onDeleteClick(todo: Todo)
        fun onItemClicked(todo: Todo)
    }

    inner class TodoViewHolder(val binding: TodoItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            TodoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentItem = todoList[position]
        holder.binding.tvTitle.text = currentItem.title
        holder.binding.tvDesc.text = currentItem.desc

        holder.binding.imgDelete.setOnClickListener{
            listener.onDeleteClick(currentItem)
        }

        holder.itemView.setOnClickListener{
                listener.onItemClicked(currentItem)
        }

    }

    fun setTodoList(todo:List<Todo>){
        todoList = todo
        notifyDataSetChanged()
    }



}