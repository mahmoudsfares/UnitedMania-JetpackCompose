package com.example.unitedmania_jetpackcompose

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
import com.example.unitedmania_jetpackcompose.navigation.NavGraph
import com.example.unitedmania_jetpackcompose.ui.theme.UnitedManiaJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

// TODO 10: di - annotate the activity as the entry point of the app
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitedManiaJetpackComposeTheme {
                // TODO 7: navigation - initiate the nav graph in the main activity
                NavGraph()
            }
        }
    }
}