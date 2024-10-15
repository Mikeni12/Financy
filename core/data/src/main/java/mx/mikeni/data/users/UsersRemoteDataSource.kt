package mx.mikeni.data.users

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

internal class UsersRemoteDataSource(private val firebaseFirestore: FirebaseFirestore) : IUsersRemoteDataSource {

    override suspend fun getUser(userId: String): Result<User> = try {
        val result = firebaseFirestore.collection("users").document(userId).get().await()
        val userResponse = result.toObject(UserResponse::class.java)
        val user = userResponse?.toUser(userId)
        if (user != null) Result.success(user) else Result.failure(Throwable("User not found"))
    } catch (e: Exception) {
        Result.failure(e)
    }
}