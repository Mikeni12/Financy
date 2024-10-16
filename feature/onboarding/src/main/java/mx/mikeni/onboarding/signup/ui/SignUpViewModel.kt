package mx.mikeni.onboarding.signup.ui

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.mikeni.onboarding.signin.ui.SignInViewModel.Companion.EMAIL_PATTERN
import mx.mikeni.onboarding.signin.ui.SignInViewModel.Companion.PASSWORD_PATTERN
import mx.mikeni.onboarding.signup.domain.ISignUpUseCase
import mx.mikeni.ui.CoroutinesDispatchers

class SignUpViewModel(private val signUpUseCase: ISignUpUseCase,
                      private val coroutinesDispatchers: CoroutinesDispatchers) : ViewModel() {

    private val _signUpFormUiModel = MutableStateFlow(SignUpFormUiModel())

    val signUpFormUiModel: StateFlow<SignUpFormUiModel>
        get() = _signUpFormUiModel

    private val _signUpUiModel = MutableStateFlow(SignUpUiModel())

    val signUpUiModel: StateFlow<SignUpUiModel>
        get() = _signUpUiModel

    fun validateForm(email: String, password: String, name: String, lastName: String) {
        emitSignUpFormUiState(showProgress = true)
        if (!EMAIL_PATTERN.toRegex().matches(email)) return emitSignUpFormUiState(isEmailError = true)
        if (!PASSWORD_PATTERN.toRegex().matches(password)) return emitSignUpFormUiState(isPasswordError = true)
        name.ifBlank { return emitSignUpFormUiState(isNameError = true) }
        lastName.ifBlank { return emitSignUpFormUiState(isLastNameError = true) }
        emitSignUpFormUiState(isFormValid = true)
    }

    private fun emitSignUpFormUiState(showProgress: Boolean = false,
                                      isEmailError: Boolean = false,
                                      isPasswordError: Boolean = false,
                                      isNameError: Boolean = false,
                                      isLastNameError: Boolean = false,
                                      isFormValid: Boolean = false) = _signUpFormUiModel.update {
        SignUpFormUiModel(showProgress, isEmailError, isPasswordError, isNameError, isLastNameError, isFormValid)
    }

    fun signUp(email: String, password: String, name: String, lastName: String, photoId: Uri) {
        emitSignUpUiState(showProgress = true)
        viewModelScope.launch(coroutinesDispatchers.io) {
            val result = signUpUseCase.signUp(email, password, name, lastName, photoId)
            withContext(coroutinesDispatchers.main) {
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
