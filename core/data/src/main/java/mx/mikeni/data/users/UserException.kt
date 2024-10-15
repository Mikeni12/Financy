package mx.mikeni.data.users

sealed class UserException(message: String) : Throwable(message) {
    data object UserNotFoundException : UserException("User not found") {
        private fun readResolve(): Any = UserNotFoundException
    }
}
