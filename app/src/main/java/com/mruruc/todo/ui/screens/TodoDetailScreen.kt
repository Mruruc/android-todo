package com.mruruc.todo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.mruruc.todo.repository.TodoRepository


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(
    modifier: Modifier = Modifier,
    id: Int,
    todoRepository: TodoRepository,
    navController: NavHostController
) {

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.Home, contentDescription = "TodoList")
                }
            },
            title = { Text("Update or Delete") })
    })
    { innerPadding ->
        if (id == -1) {
            TodoNotFound(
                modifier = modifier.padding(innerPadding),
                onBack = { navController.popBackStack() })
        } else {
            val todo = todoRepository.getTodoById(id)
            var title by remember { mutableStateOf(todo.title) }
            var description by remember { mutableStateOf(todo.description) }

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(vertical = 20.dp)
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(
                        onClick = {
                            todoRepository.updateTodo(
                                todo.copy(
                                    id = id,
                                    title = title,
                                    description = description
                                )
                            )
                        },
                    ) {
                        Text("Update")
                    }
                    Button(
                        onClick = {
                            todoRepository.deleteTodo(id)
                            navController.popBackStack()
                        },
                        colors = ButtonColors(
                            containerColor = Color.Red, contentColor = Color.White,
                            Color.Transparent, Color.Transparent
                        )
                    ) {
                        Text("Delete")
                    }
                }
            }

        }
    }

}

@Composable
fun TodoNotFound(modifier: Modifier, onBack: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp), verticalArrangement = Arrangement.Top
    ) {
        Text("Todo not found")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onBack) {
            Text("Back")
        }

    }
}

