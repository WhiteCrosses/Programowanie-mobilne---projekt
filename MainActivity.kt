package com.example.programowanie_mobilne_app_1

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.example.programowanie_mobilne_app_1.ui.theme.Programowanie_Mobilne_App_1Theme
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Programowanie_Mobilne_App_1Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                val navController = rememberNavController()
                MainComposable(navController)
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = "main_screen"){
        composable("main_screen"){
            MainScreen(navController)
        }
        composable("second_screen"){
            SecondScreen(navController)
        }
        composable("third_screen"){
            ThirdScreen(navController)
        }
    }
}

object GlobalIterator {
    private val _count = mutableStateOf(0)
    val count: Int
        get() = _count.value

    fun incrementCount(){
        _count.value = _count.value + 1
    }
}


@Composable
fun MainScreen(navController: NavController) {
    Text(
            text = "Hello main screen" + GlobalIterator.count.toString()
    )
}

@Composable
fun SecondScreen(navController: NavController) {

    Text(
        text = "Hello second screen "
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirdScreen(navController: NavController) {
    var name_of_expense by remember { mutableStateOf("Hello") }
    var amount_of_expense by remember { mutableStateOf("h3l00") }
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(10.dp)){
            TextField(value = name_of_expense, onValueChange = {name_of_expense = it})
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(10.dp)){
            TextField(value = amount_of_expense, onValueChange = {amount_of_expense = it})
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(10.dp)){
            Button(onClick = {
                //adding expense
            }) {
                Text(text = "Add")
            }}
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainComposable(navController: NavHostController){
    var iter by remember { mutableStateOf(0) }


    Scaffold (
        bottomBar = { BottomAppBar(
            actions = {
                Button(
                    onClick = {navController.navigate("main_screen")
                        GlobalIterator.incrementCount()}
                ){
                    Text(text = "Home")
                }
                Button(
                    onClick = {navController.navigate("second_screen")
                        GlobalIterator.incrementCount()}){
                    Text(text = "Expenses list")
                }
                Button(
                    onClick = {navController.navigate("third_screen")
                        GlobalIterator.incrementCount()}){
                    Text(text = "Add expense")
                }

            })
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
            ){
                Navigation(navController)
            }})
}