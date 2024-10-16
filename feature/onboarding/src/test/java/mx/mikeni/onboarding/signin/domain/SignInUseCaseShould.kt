package mx.mikeni.onboarding.signin.domain

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlin.test.assertIs
import kotlinx.coroutines.test.runTest
import mx.mikeni.ANY_USER_EMAIL
import mx.mikeni.ANY_USER_ID
import mx.mikeni.ANY_USER_PASSWORD
import mx.mikeni.data.auth.AuthException.SignInException
import mx.mikeni.data.auth.IAuthRepository
import org.junit.Before
import org.junit.Test

class SignInUseCaseShould {
    
    @MockK
    private val authRepository = mockk<IAuthRepository>()
    
    @InjectMockKs
    private var signInUseCase = SignInUseCase(authRepository)
    
    @Before
    fun setUp() = MockKAnnotations.init(this, true)
    
    @Test
    fun `Return User Id when signIn is called`() = runTest {
        coEvery { authRepository.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD) } returns Result.success(ANY_USER_ID)
        
        val result = signInUseCase.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD).getOrNull()
        
        coVerify { authRepository.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD) }
        assertEquals(ANY_USER_ID, result)
    }

    @Test
    fun `Return error when signIn is called`() = runTest {
        coEvery { authRepository.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD) } returns Result.failure(SignInException)

        val result = authRepository.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD).exceptionOrNull()

        coVerify { authRepository.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD) }
        assertIs<SignInException>(result)
    }
}
