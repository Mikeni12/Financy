package mx.mikeni.data.auth

data class AuthRequest(val email: String,
                       val password: String,
                       val name: String,
                       val lastName: String,
                       val photoUrl: String)

fun AuthRequest.toAuthMap() = mapOf(
        AUTH_EMAIL to email,
        AUTH_PASSWORD to password,
        AUTH_NAME to name,
        AUTH_LAST_NAME to lastName,
        AUTH_PHOTO_ID to photoUrl,
        AUTH_MOVEMENTS to emptyList<String>())

private const val AUTH_EMAIL = "email"
private const val AUTH_PASSWORD = "password"
private const val AUTH_NAME = "name"
private const val AUTH_LAST_NAME = "lastName"
private const val AUTH_PHOTO_ID = "photoId"
private const val AUTH_MOVEMENTS = "movements"
