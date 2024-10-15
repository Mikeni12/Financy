package mx.mikeni.financy

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.mikeni.home.HomeScreen
import mx.mikeni.onboarding.signin.ui.SignInScreen
import mx.mikeni.onboarding.signup.SignUpScreen

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val screenModifier = Modifier.fillMaxSize()
    NavHost(
            navController = navController,
            startDestination = Home,
            modifier = modifier,
            contentAlignment = Alignment.Center

    ) {
        composable<Home> {
            HomeScreen(screenModifier) {
                navController.navigate(SignIn)
            }
        }
        composable<SignUp> {
            SignUpScreen(screenModifier) {
                navController.navigate(SignIn)
            }
        }
        composable<SignIn> {
            SignInScreen(
                    onSignInListener = {
                        navController.navigate(Home)
                    },
                    onSignUpListener = {
                        navController.navigate(SignUp)
                    },
                    modifier = screenModifier
            )
        }
    }
}
