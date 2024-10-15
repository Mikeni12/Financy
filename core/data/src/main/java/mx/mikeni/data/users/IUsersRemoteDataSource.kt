package mx.mikeni.data.users

import android.net.Uri
import mx.mikeni.data.auth.AuthRequest

interface IUsersRemoteDataSource {
    suspend fun setPhotoId(photoId: Uri): Result<String>
    suspend fun getUser(userId: String): Result<User>
    suspend fun setUser(userId: String, authRequest: AuthRequest): Result<Unit>
}
