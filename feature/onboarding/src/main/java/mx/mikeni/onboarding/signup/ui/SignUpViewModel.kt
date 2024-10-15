package mx.mikeni.onboarding.signup.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.mikeni.onboarding.signup.domain.ISignUpUseCase

class SignUpViewModel(private val signUpUseCase: ISignUpUseCase) : ViewModel() {

    private val _signUpUiModel = MutableStateFlow(SignUpUiModel())

    val signUpUiModel: StateFlow<SignUpUiModel>
        get() = _signUpUiModel

    fun signUp(email: String, password: String) {
        emitSignUpUiState(showProgress = true)
        viewModelScope.launch(Dispatchers.IO) {
            val result = signUpUseCase.signUp(email, password)
            withContext(Dispatchers.Main) {
                result.onSuccess { signUpSuccess(it) }
                        .onFailure { signUpFailure(it) }
            }
        }
    }

    private fun signUpSuccess(userId: String) {
        emitSignUpUiState(userId = userId)
    }

    private fun signUpFailure(throwable: Throwable) {
        emitSignUpUiState(throwable = throwable)
    }

    private fun emitSignUpUiState(showProgress: Boolean = false, userId: String? = null, throwable: Throwable? = null) {
        _signUpUiModel.update { SignUpUiModel(showProgress, userId, throwable) }
    }
}
