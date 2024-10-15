package mx.mikeni.home.domain

import mx.mikeni.data.users.User

interface IGetUserUseCase {
    suspend fun getUser(userId: String): Result<User>
}
