package mx.mikeni.onboarding.signin.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.mikeni.onboarding.signin.domain.ISignInUseCase
import mx.mikeni.ui.CoroutinesDispatchers

class SignInViewModel(private val signInUseCase: ISignInUseCase,
                      private val coroutinesDispatchers: CoroutinesDispatchers) : ViewModel() {

    private val _signInUiModel = MutableStateFlow(SignInUiModel())

    val signInUiModel: StateFlow<SignInUiModel>
        get() = _signInUiModel

    fun signIn(email: String, password: String) {
        _signInUiModel.update { SignInUiModel(showProgress = true) }
        if (!EMAIL_PATTERN.toRegex().matches(email)) return emitSignInUiState(isEmailError = true)
        if (!PASSWORD_PATTERN.toRegex().matches(password)) return emitSignInUiState(isPasswordError = true)
        viewModelScope.launch(coroutinesDispatchers.io) {
            val result = signInUseCase.signIn(email, password)
            withContext(coroutinesDispatchers.main) {
                result.onSuccess { signInSuccess(it) }
                        .onFailure { signInFailure(it) }
            }
        }
    }

    private fun signInSuccess(userId: String) {
        emitSignInUiState(userId = userId)
    }

    private fun signInFailure(throwable: Throwable) {
        emitSignInUiState(throwable = throwable)
    }

    private fun emitSignInUiState(showProgress: Boolean = false,
                                  isEmailError: Boolean = false,
                                  isPasswordError: Boolean = false,
                                  userId: String? = null,
                                  throwable: Throwable? = null) = _signInUiModel.update {
        SignInUiModel(
                showProgress = showProgress,
                isEmailError = isEmailError,
                isPasswordError = isPasswordError,
                userId = userId,
                error = throwable)
    }

    companion object {
        const val EMAIL_PATTERN = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$"
        const val PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"
    }
}
