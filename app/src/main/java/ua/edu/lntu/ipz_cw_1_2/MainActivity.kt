package ua.edu.lntu.ipz_cw_1_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.benchmark.perfetto.ExperimentalPerfettoTraceProcessorApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ua.edu.lntu.ipz_cw_1_2.ui.theme.IPZ_CW_1_2_Voloshyn_VladTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPZ_CW_1_2_Voloshyn_VladTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination="first_screen"){
                        composable("first_screen") {
                            Greeting(navController)
                        }
                        composable("second_screen") {
                            Screen2(navController)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(navController: NavHostController) {
    var text by remember { mutableStateOf("") }
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Sing In",
                        fontSize = 24.sp
                    )
                })
        }
    ){
        paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            TextField(
                value = text1,
                onValueChange = {text1 = it},
                label = {Text("Введіть email")}
            )
            TextField(
                value = text2,
                onValueChange = {text2 = it},
                label = {Text("Введіть password")}
            )
            Text(text = text)
            Row(

            ){
                Button(onClick = {
                    if(text1 != "" && text2 != ""){
                        text = "Успішна авторизація"
                    }
                    else{
                        text = "Помилка авторизації"
                    }
                }) {
                    Text("Sing In")

                }
                Button(onClick = {
                    navController.navigate("second_screen")
                }) {
                    Text("Sing Up")

                }
            }

        }
    }
}

@Composable
fun Screen2(navController: NavHostController) {
    var text by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Sing Up", fontSize = 24.sp)
                },
                actions = {
                    IconButton(onClick = { navController.navigate("first_screen")},){
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Action Icon")
                    }
                })

        }
    ){
            paddingValues ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
        ) {
            TextField(
                value = email,
                onValueChange = {email = it},
                label = {Text("Введіть email")}
            )
            TextField(
                value = password,
                onValueChange = {password = it},
                label = {Text("Введіть password")}
            )
            Text(text = text)
            Button(onClick = {
                if(email != "" && password != ""){
                    text = "Успішна реєстрація"
                    navController.navigate("first_screen")
                }
                else{
                    text = "Помилка реєстрації"
                }
            }) {
                Text("Sing In",
                    modifier = Modifier)

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IPZ_CW_1_2_Voloshyn_VladTheme {
        Greeting()
    }
}
