package com.pavellukyanov.themartiancompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pavellukyanov.themartiancompose.R
import com.pavellukyanov.themartiancompose.ui.features.home.HomeScreen
import com.pavellukyanov.themartiancompose.ui.theme.TheMartianComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMartianComposeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = getString(R.string.nav_home)) {
                    composable(route = getString(R.string.nav_home)) {
                        Scaffold(
                            topBar = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Text(
                                        text = stringResource(
                                            id = R.string.app_name
                                        ),
                                        modifier = Modifier
                                            .padding(start = 16.dp, top = 16.dp)
                                            .align(Alignment.CenterStart)
                                    )
                                    IconButton(
                                        onClick = { },
                                        modifier = Modifier
                                            .padding(end = 16.dp, top = 16.dp)
                                            .align(Alignment.CenterEnd)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Favorite,
                                            contentDescription = getString(R.string.icon_favourites_content_description)
                                        )
                                    }
                                }
                            }
                        ) { padding ->
                            HomeScreen(navController = navController, padding = padding)
                        }
                    }
                }
            }
        }
    }
}