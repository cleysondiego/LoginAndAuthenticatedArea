package br.com.cleyson.loginandauthenticatedarea.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import br.com.cleyson.loginandauthenticatedarea.Screen
import br.com.cleyson.loginandauthenticatedarea.screens.LoginScreen
import br.com.cleyson.loginandauthenticatedarea.screens.RegisterScreen

fun NavGraphBuilder.loginRegisterNavGraph(
    navController: NavHostController,
    onLogin: () -> Unit
) {
    navigation(
        startDestination = Screen.LoginScreen.route,
        route = NavRoutes.LOGIN_REGISTER_ROUTE
    ) {
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                onLoginClicked = {
                    onLogin()
                    navController.navigateAndClearBackStack(NavRoutes.AUTHENTICATED_ROUTE)
                },
                onRegisterClicked = {
                    navController.navigate(Screen.RegisterScreen.route)
                }
            )
        }

        composable(Screen.RegisterScreen.route) {
            RegisterScreen(
                onBackClicked = {
                    navController.popBackStack()
                },
                onRegisterSubmitted = {
                    // Register the user here
                    // Then navigate back to login
                    navController.popBackStack()
                }
            )
        }
    }
}