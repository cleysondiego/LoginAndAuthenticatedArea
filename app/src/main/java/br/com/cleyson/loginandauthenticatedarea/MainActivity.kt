package br.com.cleyson.loginandauthenticatedarea

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import br.com.cleyson.loginandauthenticatedarea.ui.theme.LoginAndAuthenticatedAreaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginAndAuthenticatedAreaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Track authentication state
                    val isLoggedIn = remember { mutableStateOf(false) }

                    // Set up navigation
                    AppNavigation(
                        isLoggedIn = isLoggedIn.value,
                        onLogin = { isLoggedIn.value = true },
                        onLogout = { isLoggedIn.value = false }
                    )
                }
            }
        }
    }
}
