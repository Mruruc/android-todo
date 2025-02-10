package com.mruruc.todo.repository

import androidx.compose.runtime.mutableStateListOf
import com.mruruc.todo.model.Todo

class TodoRepository {
    private var nextId = 1
    private val todos = mutableStateListOf<Todo>(
        Todo(nextId++, "Shopping", "Go to shopping!")
    )

    fun addTodo(title: String, description: String) {
        todos.add(Todo(nextId++, title, description))
    }

    fun getTodos(): List<Todo> {
        return todos.toList()
    }


    fun getTodoById(id: Int): Todo {
        return todos.find { todo -> todo.id == id }!!
    }


    fun updateTodo(todo: Todo) {
        val index = todos.indexOf(todo)
        todos[index] = todo;
    }

    fun deleteTodo(id: Int) {
        todos.removeIf { todo -> todo.id == id }
    }

}