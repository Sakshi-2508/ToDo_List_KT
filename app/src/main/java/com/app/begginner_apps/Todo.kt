package com.app.begginner_apps

data class Todo(
    val id: Int = System.currentTimeMillis().toInt(),
    var title: String,
    var isCompleted: Boolean = false
)