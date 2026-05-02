package com.app.begginner_apps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.begginner_apps.databinding.ItemTodoBinding


class TodoAdapter(
    private val onCheckedChange: (Todo, Boolean) -> Unit,
    private val onDelete: (Todo) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    var todoList: MutableList<Todo> = mutableListOf()

    inner class TodoViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.tvTodoTitle.text = todo.title
            binding.checkbox.isChecked = todo.isCompleted

            binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
                onCheckedChange(todo, isChecked)
            }

            binding.btnDelete.setOnClickListener {
                onDelete(todo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    override fun getItemCount() = todoList.size

    fun addTodo(todo: Todo) {
        todoList.add(0, todo)
        notifyItemInserted(0)
    }

    fun updateTodo(todo: Todo) {
        val index = todoList.indexOfFirst { it.id == todo.id }
        if (index != -1) {
            todoList[index] = todo
            notifyItemChanged(index)
        }
    }

    fun deleteTodo(todo: Todo) {
        val index = todoList.indexOfFirst { it.id == todo.id }
        if (index != -1) {
            todoList.removeAt(index)
            notifyItemRemoved(index)
        }
    }
}