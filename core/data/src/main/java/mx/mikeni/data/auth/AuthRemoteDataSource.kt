package mx.mikeni.data.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRemoteDataSource(private val firebaseAuth: FirebaseAuth) : IAuthRemoteDataSource {

    override suspend fun signIn(email: String, password: String): Result<String> = try {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        Result.success(result.user?.uid.orEmpty())
    } catch (e: Exception) {
        Result.failure(AuthException.SignInException)
    }

    override suspend fun signUp(email: String, password: String): Result<String> = try {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        Result.success(result.user?.uid.orEmpty())
    } catch (e: Exception) {
        Result.failure(AuthException.SignUpException)
    }
}
