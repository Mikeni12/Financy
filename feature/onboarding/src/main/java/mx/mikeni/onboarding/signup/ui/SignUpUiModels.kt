package mx.mikeni.onboarding.signup.ui

data class SignUpUiModel(val showProgress: Boolean = false,
                         val userId: String? = null,
                         val error: Throwable? = null)
