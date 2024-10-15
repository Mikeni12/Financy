package mx.mikeni.data.users

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import mx.mikeni.data.auth.AuthRequest
import mx.mikeni.data.auth.toAuthMap

internal class UsersRemoteDataSource(private val firebaseFirestore: FirebaseFirestore,
                                     private val firebaseStorage: FirebaseStorage) : IUsersRemoteDataSource {

    override suspend fun setPhotoId(photoId: Uri): Result<String> = try {
        val storageRef = firebaseStorage.reference.child("images/${photoId.lastPathSegment}")
        val result = storageRef.putFile(photoId).await()
        val downloadUrl = result.storage.downloadUrl.await()
        Result.success(downloadUrl.toString())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getUser(userId: String): Result<User> = try {
        val result = firebaseFirestore.collection(USERS_COLLECTION).document(userId).get().await()
        val userResponse = result.toObject(UserResponse::class.java)
        val user = userResponse?.toUser(userId)
        if (user != null) Result.success(user) else Result.failure(Throwable("User not found"))
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun setUser(userId: String, authRequest: AuthRequest): Result<Unit> = try {
        firebaseFirestore.collection(USERS_COLLECTION).document(userId).set(authRequest.toAuthMap()).await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    companion object {
        private const val USERS_COLLECTION = "users"
    }
}
