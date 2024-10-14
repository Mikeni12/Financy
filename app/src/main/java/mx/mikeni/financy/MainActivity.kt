package mx.mikeni.financy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import mx.mikeni.data.AuthRemoteDataSource
import mx.mikeni.data.UsersRemoteDataSource
import mx.mikeni.financy.ui.theme.FinancyTheme

class MainActivity : ComponentActivity() {

    val usersRemoteDataSource = UsersRemoteDataSource(Firebase.firestore)
    val authRemoteDataSource = AuthRemoteDataSource(Firebase.auth)

    val user = hashMapOf(
            "name" to "Alan",
            "surname" to "Turing",
            "email" to "alan_turing@gmail.com",
            "password" to "AlanTuring1912",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        lifecycleScope.launch {
            signIn()
        }
        setContent {
            FinancyTheme {
                MainScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }

    fun createUserFirestore(): Unit {
        Firebase.firestore.collection("users")
                .document(user["email"].orEmpty())
                .set(user)
    }

    suspend fun signIn() {
        authRemoteDataSource.signIn(user["email"].orEmpty(), user["password"].orEmpty())
    }
}
