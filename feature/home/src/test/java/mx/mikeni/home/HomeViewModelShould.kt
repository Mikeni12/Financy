package mx.mikeni.home

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import mx.mikeni.ANY_USER_ID
import mx.mikeni.home.domain.IGetUserUseCase
import mx.mikeni.home.ui.HomeUiModel
import mx.mikeni.home.ui.HomeViewModel
import mx.mikeni.testing.CoroutineScopeRule
import org.junit.Rule
import org.junit.Test

class HomeViewModelShould {

    @get:Rule
    val coroutineScopeRule = CoroutineScopeRule()

    @MockK
    private val getUserUseCase: IGetUserUseCase = mockk()

    @InjectMockKs
    private var homeViewModel = HomeViewModel(
            getUserUseCase = getUserUseCase,
            coroutinesDispatchers = coroutineScopeRule.coroutinesDispatchers)

    @Test
    fun `Return user when getUser is called`() = runTest {
        val userUi = givenUserUi()
        val user = givenUser()
        val homeUiModelState = HomeUiModel(userUi = userUi)
        coEvery { getUserUseCase.getUser(ANY_USER_ID) } returns Result.success(user)

        homeViewModel.getUser(ANY_USER_ID)

        coVerify { getUserUseCase.getUser(ANY_USER_ID) }
        assertEquals(homeUiModelState, homeViewModel.homeUiModel.value)
    }

    @Test
    fun `Return failure when getUser is called`() = runTest {
        val error = Throwable()
        val homeUiModelState = HomeUiModel(error = error)
        coEvery { getUserUseCase.getUser(ANY_USER_ID) } returns Result.failure(error)

        homeViewModel.getUser(ANY_USER_ID)

        coVerify { getUserUseCase.getUser(ANY_USER_ID) }
        assertEquals(homeUiModelState, homeViewModel.homeUiModel.value)
    }
}
