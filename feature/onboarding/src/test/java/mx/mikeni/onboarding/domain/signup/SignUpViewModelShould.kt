package mx.mikeni.onboarding.domain.signup

import android.net.Uri
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import mx.mikeni.ANY_USER_EMAIL
import mx.mikeni.ANY_USER_ID
import mx.mikeni.ANY_USER_LAST_NAME
import mx.mikeni.ANY_USER_NAME
import mx.mikeni.ANY_USER_PASSWORD
import mx.mikeni.onboarding.signup.domain.ISignUpUseCase
import mx.mikeni.onboarding.signup.ui.SignUpUiModel
import mx.mikeni.onboarding.signup.ui.SignUpViewModel
import mx.mikeni.testing.CoroutineScopeRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpViewModelShould {

    @get:Rule
    val coroutineScopeRule = CoroutineScopeRule()

    @MockK
    private val signUpUseCase: ISignUpUseCase = mockk()

    @MockK
    private val photoId: Uri = mockk(relaxed = true)

    @InjectMockKs
    private var signUpViewModel = SignUpViewModel(
            signUpUseCase = signUpUseCase,
            coroutinesDispatchers = coroutineScopeRule.coroutinesDispatchers)

    @Before
    fun setUp() = MockKAnnotations.init(this, true)

    @Test
    fun `Return User Id when signUp is called`() = runTest {
        val signUpUiModelState = SignUpUiModel(userId = ANY_USER_ID)
        coEvery { signUpUseCase.signUp(ANY_USER_EMAIL, ANY_USER_PASSWORD, ANY_USER_NAME, ANY_USER_LAST_NAME, photoId) } returns
                Result.success(ANY_USER_ID)

        signUpViewModel.signUp(ANY_USER_EMAIL, ANY_USER_PASSWORD, ANY_USER_NAME, ANY_USER_LAST_NAME, photoId)

        coVerify { signUpUseCase.signUp(ANY_USER_EMAIL, ANY_USER_PASSWORD, ANY_USER_NAME, ANY_USER_LAST_NAME, photoId) }
        assertEquals(signUpUiModelState, signUpViewModel.signUpUiModel.value)
    }

    @Test
    fun `Return failure when signUp is called`() = runTest {
        val error = Throwable()
        val signUpUiModelState = SignUpUiModel(error = error)
        coEvery { signUpUseCase.signUp(ANY_USER_EMAIL, ANY_USER_PASSWORD, ANY_USER_NAME, ANY_USER_LAST_NAME, photoId) } returns
                Result.failure(error)

        signUpViewModel.signUp(ANY_USER_EMAIL, ANY_USER_PASSWORD, ANY_USER_NAME, ANY_USER_LAST_NAME, photoId)

        coVerify { signUpUseCase.signUp(ANY_USER_EMAIL, ANY_USER_PASSWORD, ANY_USER_NAME, ANY_USER_LAST_NAME, photoId) }
        assertEquals(signUpUiModelState, signUpViewModel.signUpUiModel.value)
    }
}
