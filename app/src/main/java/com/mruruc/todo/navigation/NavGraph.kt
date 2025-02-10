package com.mruruc.todo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mruruc.todo.repository.TodoRepository
import com.mruruc.todo.ui.screens.AddTodoScreen
import com.mruruc.todo.ui.screens.TodoDetailScreen
import com.mruruc.todo.ui.screens.TodoListScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    repository: TodoRepository = TodoRepository()
) {

    NavHost(navController = navController, startDestination = "todo-list") {

        composable("todo-list") {
            TodoListScreen(
                todos = repository.getTodos(),
                navController = navController,
            )
        }

        composable("add-todo") {
            AddTodoScreen(repository = repository, navController = navController)
        }

        composable(
            route = "todo-detail/{todoId}",
            arguments = listOf(navArgument("todoId") {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val todoId = backStackEntry.arguments?.getInt("todoId") ?: -1;
            TodoDetailScreen(
                id = todoId,
                todoRepository = repository,
                navController = navController
            )
        }

    }
}