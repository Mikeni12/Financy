package mx.mikeni.data.auth

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlinx.coroutines.test.runTest
import mx.mikeni.ANY_USER_EMAIL
import mx.mikeni.ANY_USER_ID
import mx.mikeni.ANY_USER_PASSWORD
import mx.mikeni.data.auth.AuthException.SignInException
import mx.mikeni.data.auth.AuthException.SignUpException
import org.junit.Before
import org.junit.Test

class AuthRepositoryShould {

    @MockK
    private val authRemoteDataSource = mockk<IAuthRemoteDataSource>()

    @InjectMockKs
    private var authRepository = AuthRepository(authRemoteDataSource)

    @Before
    fun setUp() = MockKAnnotations.init(this, true)

    @Test
    fun `Return User Id when signIn is called`() = runTest {
        coEvery { authRemoteDataSource.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD) } returns Result.success(ANY_USER_ID)

        val result = authRepository.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD).getOrNull()

        coVerify { authRemoteDataSource.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD) }
        assertEquals(ANY_USER_ID, result)
    }

    @Test
    fun `Return error when signIn is called`() = runTest {
        coEvery { authRemoteDataSource.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD) } returns Result.failure(SignInException)

        val result = authRepository.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD).exceptionOrNull()

        coVerify { authRemoteDataSource.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD) }
        assertIs<SignInException>(result)
    }

    @Test
    fun `Return User Id when signUp is called`() = runTest {
        coEvery { authRemoteDataSource.signUp(ANY_USER_EMAIL, ANY_USER_PASSWORD) } returns Result.success(ANY_USER_ID)

        val result = authRepository.signUp(ANY_USER_EMAIL, ANY_USER_PASSWORD).getOrNull()

        coVerify { authRemoteDataSource.signUp(ANY_USER_EMAIL, ANY_USER_PASSWORD) }
        assertEquals(ANY_USER_ID, result)
    }

    @Test
    fun `Return error when signUp is called`() = runTest {
        coEvery { authRemoteDataSource.signUp(ANY_USER_EMAIL, ANY_USER_PASSWORD) } returns Result.failure(SignUpException)

        val result = authRepository.signUp(ANY_USER_EMAIL, ANY_USER_PASSWORD).exceptionOrNull()

        coVerify { authRemoteDataSource.signUp(ANY_USER_EMAIL, ANY_USER_PASSWORD) }
        assertIs<SignUpException>(result)
    }
}
