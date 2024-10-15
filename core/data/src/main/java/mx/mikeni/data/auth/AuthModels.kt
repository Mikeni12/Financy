package mx.mikeni.data.auth

data class AuthRequest(val email: String,
                       val password: String,
                       val name: String,
                       val lastName: String,
                       val photoUrl: String)

fun AuthRequest.toAuthMap() = mapOf(
        "email" to email,
        "password" to password,
        "name" to name,
        "lastName" to lastName,
        "photoId" to photoUrl,
        "movements" to emptyList<String>())
