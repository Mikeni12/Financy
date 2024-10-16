package mx.mikeni.onboarding.signup.ui

data class SignUpFormUiModel(val showProgress: Boolean = false,
                             val isEmailError: Boolean = false,
                             val isPasswordError: Boolean = false,
                             val isNameError: Boolean = false,
                             val isLastNameError: Boolean = false,
                             val isFormValid: Boolean = false)

data class SignUpUiModel(val showProgress: Boolean = false,
                         val userId: String? = null,
                         val error: Throwable? = null)
