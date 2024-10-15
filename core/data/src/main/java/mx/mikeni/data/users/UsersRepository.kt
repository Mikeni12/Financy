package mx.mikeni.data.users

import android.net.Uri
import mx.mikeni.data.auth.AuthRequest

internal class UsersRepository(private val usersRemoteDataSource: IUsersRemoteDataSource) : IUsersRepository {

    override suspend fun getUser(userId: String) = usersRemoteDataSource.getUser(userId)

    override suspend fun setUser(userId: String, authRequest: AuthRequest) = usersRemoteDataSource.setUser(userId, authRequest)

    override suspend fun setPhotoId(photoId: Uri) = usersRemoteDataSource.setPhotoId(photoId)
}
