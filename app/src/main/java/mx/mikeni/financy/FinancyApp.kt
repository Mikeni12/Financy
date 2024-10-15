package mx.mikeni.financy

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import mx.mikeni.home.ui.HomeScreen
import mx.mikeni.onboarding.signin.ui.SignInScreen
import mx.mikeni.onboarding.signup.ui.SignUpScreen

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val screenModifier = Modifier.fillMaxSize()
    NavHost(
            navController = navController,
            startDestination = SignIn,
            modifier = modifier,
            contentAlignment = Alignment.Center

    ) {
        composable<SignIn> {
            SignInScreen(
                    onSignInListener = {
                        navController.navigate(Home(it))
                    },
                    onSignUpListener = {
                        navController.navigate(SignUp)
                    },
                    modifier = screenModifier
            )
        }
        composable<SignUp> {
            SignUpScreen(screenModifier) {
                navController.navigate(Home(it))
            }
        }
        composable<Home> {
            val arguments = it.toRoute<Home>()
            HomeScreen(
                    userId = arguments.userId,
                    modifier = screenModifier
            ) {
                navController.navigate(SignIn)
            }
        }
    }
}
