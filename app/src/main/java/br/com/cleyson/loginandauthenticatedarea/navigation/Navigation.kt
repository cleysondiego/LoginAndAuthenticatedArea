package br.com.cleyson.loginandauthenticatedarea.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

object NavRoutes {
    const val LOGIN_REGISTER_ROUTE = "loginRoute"
    const val AUTHENTICATED_ROUTE = "authenticatedRoute"
    const val MAIN_ROUTE = "mainRoute"
}

@Composable
fun AppNavigation(
    isLoggedIn: Boolean,
    onLogin: () -> Unit,
    onLogout: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) NavRoutes.AUTHENTICATED_ROUTE else NavRoutes.LOGIN_REGISTER_ROUTE
    ) {
        // Unauthenticated area (Login/Register)
        loginRegisterNavGraph(
            navController = navController,
            onLogin = onLogin
        )

        // Authenticated area (Home/Settings with bottom navigation)
        mainNavGraph(
            navController = navController,
            onLogout = onLogout
        )
    }
}

// Helper function to navigate and clear the backstack
fun NavHostController.navigateAndClearBackStack(route: String) {
    navigate(route) {
        popUpTo(0) { inclusive = true }
    }
}