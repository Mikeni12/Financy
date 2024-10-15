package mx.mikeni.data.auth

sealed class AuthException(message: String) : Throwable(message) {
    data object SignInException : AuthException("Couldn't sign in due to invalid credentials") {
        private fun readResolve(): Any = SignInException
    }

    data object SignUpException : AuthException("Couldn't sign up due to invalid credentials") {
        private fun readResolve(): Any = SignUpException
    }

    data object UserNotFoundException : AuthException("User not found") {
        private fun readResolve(): Any = UserNotFoundException
    }
}
