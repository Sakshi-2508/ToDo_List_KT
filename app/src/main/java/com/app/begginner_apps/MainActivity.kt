package com.app.begginner_apps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup Toolbar
        setSupportActionBar(findViewById(R.id.toolbar))

        // Setup Adapter
        todoAdapter = TodoAdapter(
            onCheckedChange = { todo, isChecked ->
                todo.isCompleted = isChecked
                todoAdapter.updateTodo(todo)
            },
            onDelete = { todo ->
                todoAdapter.deleteTodo(todo)
            }
        )

        // Setup RecyclerView
        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = todoAdapter

        // Add Button
        findViewById<android.widget.Button>(R.id.btnAdd).setOnClickListener {
            val etTodo = findViewById<com.google.android.material.textfield.TextInputEditText>(R.id.etTodo)
            val todoText = etTodo.text.toString().trim()

            if (todoText.isNotEmpty()) {
                val newTodo = Todo(title = todoText)
                todoAdapter.addTodo(newTodo)
                etTodo.text?.clear()
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Please enter a task", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}