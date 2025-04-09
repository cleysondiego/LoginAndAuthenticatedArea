package br.com.cleyson.loginandauthenticatedarea

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

// Login/Register navigation routes
object LoginRegisterRoutes {
    const val LOGIN = "login"
    const val REGISTER = "register"
}

fun NavGraphBuilder.loginRegisterNavGraph(
    navController: NavHostController,
    onLogin: () -> Unit
) {
    navigation(
        startDestination = LoginRegisterRoutes.LOGIN,
        route = NavRoutes.LOGIN_REGISTER_ROUTE
    ) {
        composable(LoginRegisterRoutes.LOGIN) {
            LoginScreen(
                onLoginClicked = {
                    onLogin()
                    navController.navigateAndClearBackStack(NavRoutes.AUTHENTICATED_ROUTE)
                },
                onRegisterClicked = {
                    navController.navigate(LoginRegisterRoutes.REGISTER)
                }
            )
        }

        composable(LoginRegisterRoutes.REGISTER) {
            RegisterScreen(
                onBackClicked = {
                    navController.popBackStack()
                },
                onRegisterSubmitted = {
                    // In a real app, you would register the user here
                    // Then navigate back to login
                    navController.popBackStack()
                }
            )
        }
    }
}