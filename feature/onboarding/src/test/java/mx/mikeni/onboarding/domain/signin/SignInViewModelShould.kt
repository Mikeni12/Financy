package mx.mikeni.onboarding.domain.signin

import io.mockk.Called
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest
import mx.mikeni.ANY_USER_EMAIL
import mx.mikeni.ANY_USER_ID
import mx.mikeni.ANY_USER_NAME
import mx.mikeni.ANY_USER_PASSWORD
import mx.mikeni.onboarding.signin.domain.ISignInUseCase
import mx.mikeni.onboarding.signin.ui.SignInUiModel
import mx.mikeni.onboarding.signin.ui.SignInViewModel
import mx.mikeni.testing.CoroutineScopeRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignInViewModelShould {

    @get:Rule
    val coroutineScopeRule = CoroutineScopeRule()

    @MockK
    private val signInUseCase: ISignInUseCase = mockk()

    @InjectMockKs
    private var signInViewModel = SignInViewModel(
            signInUseCase = signInUseCase,
            coroutinesDispatchers = coroutineScopeRule.coroutinesDispatchers)

    @Before
    fun setUp() = MockKAnnotations.init(this, true)

    @Test
    fun `Sign in with email and password when signIn is called`() = runTest {
        val signInUiModelState = SignInUiModel(userId = ANY_USER_ID)
        coEvery { signInUseCase.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD) } returns Result.success(ANY_USER_ID)

        signInViewModel.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD)

        coVerify { signInUseCase.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD) }
        assertEquals(signInUiModelState, signInViewModel.signInUiModel.value)
    }

    @Test
    fun `Return error on wrong email when signIn is called`() = runTest {
        val signInUiModelState = SignInUiModel(isEmailError = true)

        signInViewModel.signIn(ANY_USER_NAME, ANY_USER_PASSWORD)

        coVerify { signInUseCase wasNot Called }
        assertEquals(signInUiModelState, signInViewModel.signInUiModel.value)
    }

    @Test
    fun `Return error on wrong password when signIn is called`() = runTest {
        val signInUiModelState = SignInUiModel(isPasswordError = true)

        signInViewModel.signIn(ANY_USER_EMAIL, ANY_USER_NAME)

        coVerify { signInUseCase wasNot Called }
        assertEquals(signInUiModelState, signInViewModel.signInUiModel.value)
    }

    @Test
    fun `Return failure when signIn is called`() = runTest {
        val error = Throwable()
        val signInUiModelState = SignInUiModel(error = error)
        coEvery { signInUseCase.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD) } returns Result.failure(error)

        signInViewModel.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD)

        coVerify { signInUseCase.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD) }
        assertEquals(signInUiModelState, signInViewModel.signInUiModel.value)
    }
}
