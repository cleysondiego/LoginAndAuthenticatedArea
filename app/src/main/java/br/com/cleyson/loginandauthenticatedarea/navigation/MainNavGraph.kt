package br.com.cleyson.loginandauthenticatedarea.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import br.com.cleyson.loginandauthenticatedarea.Screen
import br.com.cleyson.loginandauthenticatedarea.screens.HomeScreen
import br.com.cleyson.loginandauthenticatedarea.screens.SettingsScreen

// Bottom navigation items
sealed class BottomNavItem(val route: String, val icon: @Composable () -> Unit, val title: String) {
    object Home : BottomNavItem(
        route = Screen.HomeScreen.route,
        icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
        title = "Home"
    )

    object Settings : BottomNavItem(
        route = Screen.SettingsScreen.route,
        icon = { Icon(Icons.Default.Settings, contentDescription = "Settings") },
        title = "Settings"
    )
}

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController,
    onLogout: () -> Unit
) {
    navigation(
        startDestination = NavRoutes.MAIN_ROUTE,
        route = NavRoutes.AUTHENTICATED_ROUTE
    ) {
        composable(route = NavRoutes.MAIN_ROUTE) {
            MainNavHost(
                onLogout = {
                    onLogout()
                    navController.navigateAndClearBackStack(NavRoutes.LOGIN_REGISTER_ROUTE)
                }
            )
        }
    }
}

@Composable
fun MainNavHost(onLogout: () -> Unit) {
    val navController = rememberNavController()
    val navItems = remember {
        listOf(
            BottomNavItem.Home,
            BottomNavItem.Settings
        )
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = navItems
            )
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.HomeScreen.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.HomeScreen.route) {
                HomeScreen()
            }

            composable(Screen.SettingsScreen.route) {
                SettingsScreen(onLogoutClicked = onLogout)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<BottomNavItem>
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = { item.icon() },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            // Pop up to the start destination of the graph to avoid
                            // building up a large stack of destinations
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}
