package br.com.cleyson.loginandauthenticatedarea

sealed class Screen(val route: String) {
    object LoginScreen: Screen("login")
    object RegisterScreen: Screen("register")
    object HomeScreen: Screen("home")
    object SettingsScreen: Screen("settings")
}
