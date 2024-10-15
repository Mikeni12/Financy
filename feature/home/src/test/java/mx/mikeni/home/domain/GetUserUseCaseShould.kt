package mx.mikeni.home.domain

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest
import mx.mikeni.ANY_USER_ID
import mx.mikeni.data.users.IUsersRepository
import mx.mikeni.home.givenUser
import org.junit.Before
import org.junit.Test

class GetUserUseCaseShould {

    @MockK
    private val userRepository: IUsersRepository = mockk()

    @InjectMockKs
    private var getUserUseCase = GetUserUseCase(userRepository)

    @Before
    fun setUp() = MockKAnnotations.init(this, true)

    @Test
    fun `Return User when getUser is called`() = runTest {
        val user = givenUser()
        coEvery { userRepository.getUser(ANY_USER_ID) } returns Result.success(user)

        val result = getUserUseCase.getUser(ANY_USER_ID).getOrNull()

        coEvery { userRepository.getUser(ANY_USER_ID) }
        assertEquals(user, result)
    }

    @Test
    fun `Return error when getUser is called`() = runTest {
        val error = Throwable()
        coEvery { userRepository.getUser(ANY_USER_ID) } returns Result.failure(error)

        val result = getUserUseCase.getUser(ANY_USER_ID).exceptionOrNull()

        coEvery { userRepository.getUser(ANY_USER_ID) }
        assertEquals(error, result)
    }
}
