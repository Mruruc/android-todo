package com.mruruc.todo.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.mruruc.todo.navigation.NavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController();
//    Scaffold(
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = { Text("#Todos") },
//            )
//        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = { navController.navigate("add-todo") }
//            ) {
//                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
//            }
//        },
//        bottomBar = {
//            BottomAppBar {
//                Text("Bottom Bar")
//            }
//        },
//    ) { innerPadding ->
//
//    }
    NavGraph( navController = navController)
}