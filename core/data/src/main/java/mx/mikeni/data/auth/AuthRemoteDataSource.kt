package mx.mikeni.data.auth

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRemoteDataSource(private val firebaseAuth: FirebaseAuth) : IAuthRemoteDataSource {

    override suspend fun signIn(email: String, password: String): Result<String> = runCatching {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        result.user?.uid.orEmpty()
    }

    override suspend fun signUp(email: String, password: String): Result<String> = runCatching {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        result.user?.uid.orEmpty()
    }
}
