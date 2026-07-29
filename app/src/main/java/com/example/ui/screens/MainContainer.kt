package com.example.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ui.theme.BlueAccent
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.viewmodel.PharmaViewModel

sealed class Screen(val route: String, val title: String, val icon: @Composable () -> Unit) {
    object Classifier : Screen("classifier", "Recherche", { Icon(Icons.Default.Search, contentDescription = "Search") })
    object Simulator : Screen("simulator", "Simulateur", { Icon(Icons.Default.Calculate, contentDescription = "Simulator") })
    object LegalNotes : Screen("legal_notes", "Notes 2026", { Icon(Icons.Default.MenuBook, contentDescription = "Notes") })
    object Favorites : Screen("favorites", "Favoris", { Icon(Icons.Default.Favorite, contentDescription = "Favorites") })
}

@Composable
fun MainContainer() {
    val navController = rememberNavController()
    val pharmaViewModel: PharmaViewModel = viewModel()

    val screens = listOf(
        Screen.Classifier,
        Screen.Simulator,
        Screen.LegalNotes,
        Screen.Favorites
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: Screen.Classifier.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                contentColor = NavyPrimary
            ) {
                screens.forEach { screen ->
                    val selected = currentRoute == screen.route
                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = screen.icon,
                        label = {
                            Text(
                                text = screen.title,
                                fontSize = 11.sp,
                                fontWeight = if (selected) FontWeight.ExtraBold else FontWeight.Medium
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = NavyPrimary,
                            selectedTextColor = NavyPrimary,
                            indicatorColor = Color(0xFFE0F2FE),
                            unselectedIconColor = TextSecondary,
                            unselectedTextColor = TextSecondary
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Classifier.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Classifier.route) {
                ClassifierScreen(
                    viewModel = pharmaViewModel,
                    onNavigateToSimulator = {
                        navController.navigate(Screen.Simulator.route)
                    }
                )
            }
            composable(Screen.Simulator.route) {
                SimulatorScreen(viewModel = pharmaViewModel)
            }
            composable(Screen.LegalNotes.route) {
                LegalNotesScreen()
            }
            composable(Screen.Favorites.route) {
                FavoritesScreen(
                    viewModel = pharmaViewModel,
                    onNavigateToSimulator = {
                        navController.navigate(Screen.Simulator.route)
                    }
                )
            }
        }
    }
}
