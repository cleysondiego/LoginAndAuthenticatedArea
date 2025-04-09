package br.com.cleyson.loginandauthenticatedarea

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

// Define navigation routes
object NavRoutes {
    const val LOGIN_REGISTER_ROUTE = "login_register"
    const val AUTHENTICATED_ROUTE = "authenticated"
}

@Composable
fun AppNavigation(
    isLoggedIn: Boolean,
    onLogin: () -> Unit,
    onLogout: () -> Unit
) {
    val navController = rememberNavController()

    // Define the navigation graph
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