package com.flutterjunction.junctionshop

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavArgument
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.flutterjunction.junctionshop.ui.JunctionShopNavigation
import com.flutterjunction.junctionshop.ui.detail.DetailScreen
import com.flutterjunction.junctionshop.ui.home.HomeScreen
import com.flutterjunction.junctionshop.ui.theme.JunctionShopTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JunctionShopTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JunctionShopApp()
                }
            }


//            HomeScreen(onNavigate = { id -> navHostController })
        }
    }

    @Composable
    fun JunctionShopApp() {
        JunctionShopNavigation()
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JunctionShopTheme {
        Greeting("Android")
    }
}