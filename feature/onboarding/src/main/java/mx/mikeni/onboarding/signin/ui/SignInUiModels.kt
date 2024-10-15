package mx.mikeni.onboarding.signin.ui

data class SignInUiModel(val showProgress: Boolean = false,
                         val isEmailError: Boolean = false,
                         val isPasswordError: Boolean = false,
                         val userId: String? = null,
                         val error: Throwable? = null)
