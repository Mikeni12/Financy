package mx.mikeni.home.domain

import mx.mikeni.data.users.IUsersRepository

class GetUserUseCase(private val userRepository: IUsersRepository) : IGetUserUseCase {
    override suspend fun getUser(userId: String) = userRepository.getUser(userId)
}
