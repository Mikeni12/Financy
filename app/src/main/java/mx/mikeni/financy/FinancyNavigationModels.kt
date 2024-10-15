package mx.mikeni.financy

import kotlinx.serialization.Serializable

@Serializable
data object SignIn

@Serializable
data object SignUp

@Serializable
data class Home(val userId: String)
