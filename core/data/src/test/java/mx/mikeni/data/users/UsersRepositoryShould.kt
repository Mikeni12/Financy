package mx.mikeni.data.users

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest
import mx.mikeni.ANY_USER_ID
import mx.mikeni.data.givenUser
import org.junit.Before
import org.junit.Test

class UsersRepositoryShould {

    @MockK
    private val usersRemoteDataSource = mockk<IUsersRemoteDataSource>()

    @InjectMockKs
    private var usersRepository = UsersRepository(usersRemoteDataSource)

    @Before
    fun setUp() = MockKAnnotations.init(this, true)

    @Test
    fun `Return User when getUser is called`() = runTest {
        val user = givenUser()
        coEvery { usersRemoteDataSource.getUser(ANY_USER_ID) } returns Result.success(user)

        val result = usersRepository.getUser(ANY_USER_ID).getOrNull()

        coEvery { usersRemoteDataSource.getUser(ANY_USER_ID) }
        assertEquals(user, result)
    }

    @Test
    fun `Return error when getUser is called`() = runTest {
        val error = Throwable()
        coEvery { usersRemoteDataSource.getUser(ANY_USER_ID) } returns Result.failure(error)

        val result = usersRepository.getUser(ANY_USER_ID).exceptionOrNull()

        coEvery { usersRemoteDataSource.getUser(ANY_USER_ID) }
        assertEquals(error, result)
    }
}
