package mx.mikeni.financy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import mx.mikeni.ui.FinancyTheme
import mx.mikeni.ui.GreenDark
import mx.mikeni.ui.GreenLight
import mx.mikeni.ui.Space16
import mx.mikeni.home.HomeScreen
import mx.mikeni.onboarding.signin.SignInScreen
import mx.mikeni.onboarding.signup.SignUpScreen

@Composable
fun Test(modifier: Modifier = Modifier) {
    Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                    .fillMaxWidth()
                    .background(GreenDark, shape = CircleShape)
    ) {
        IconButton(enabled = true, onClick = { }, colors = IconButtonDefaults.iconButtonColors(containerColor = GreenLight)) {
            Icon(Icons.Rounded.Add, null)
        }
        IconButton(enabled = false, onClick = { }) {
            Icon(Icons.Rounded.Edit, null)
        }
        IconButton(enabled = false, onClick = { }) {
            Icon(Icons.Rounded.Home, null)
        }
        IconButton(enabled = false, onClick = { }) {
            Icon(Icons.Rounded.Favorite, null)
        }
        IconButton(enabled = false, onClick = { }) {
            Icon(Icons.Rounded.Settings, null)
        }
    }
}

@Preview
@Composable
private fun TestPreview() {
    FinancyTheme {
        Test()
    }
}

@Serializable
data object Home

@Serializable
data object SignUp

@Serializable
data object SignIn

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val screenModifier = Modifier
            .fillMaxSize()
            .padding(Space16)
    NavHost(
            navController = navController,
            startDestination = SignIn,
            modifier = modifier,
            contentAlignment = Alignment.Center

    ) {
        composable<Home> {
            HomeScreen(screenModifier) {
                navController.navigate(SignUp)
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
