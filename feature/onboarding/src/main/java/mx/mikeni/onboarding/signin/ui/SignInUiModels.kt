package mx.mikeni.onboarding.signin.ui

data class SignInUiModel(val showProgress: Boolean = false,
                         val showSuccess: Boolean = false,
                         val error: Throwable? = null)
