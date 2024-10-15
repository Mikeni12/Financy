package mx.mikeni.data.users

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import mx.mikeni.data.ANY_USER_ID
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
    fun `Return User when getUser is called`() {
//        coEvery { usersRemoteDataSource.getUser(ANY_USER_ID) } returns Result.success(ANY_USER)
    }
}
