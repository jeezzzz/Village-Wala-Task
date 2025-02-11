package com.jeezzzz.internshiptaskvillagewala

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.jeezzzz.internshiptaskvillagewala.navigation.Navigation
import com.jeezzzz.internshiptaskvillagewala.ui.theme.InternshipTaskVillageWalaTheme

//import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InternshipTaskVillageWalaTheme {
                val navController = rememberNavController()
                Navigation(navController)
            }
        }
    }
}