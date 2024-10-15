package mx.mikeni.data.users

internal data class UserResponse(val name: String? = null,
                                 val lastName: String? = null,
                                 val email: String? = null,
                                 val photoId: String? = null,
                                 val movements: List<MovementResponse>? = null)

internal data class MovementResponse(val title: String? = null,
                                     val description: String? = null,
                                     val amount: String? = null,
                                     val category: String? = null,
                                     val imageUrl: String? = null,
                                     val dateShort: String? = null,
                                     val dateLong: String? = null,
                                     val time: String? = null,
                                     @field:JvmField
                                     val isPositive: Boolean? = null)

internal fun UserResponse.toUser(id: String) = User(
        id = id,
        name = name.orEmpty(),
        lastName = lastName.orEmpty(),
        email = email.orEmpty(),
        photoId = photoId.orEmpty(),
        movements = movements?.toMovements().orEmpty())

private fun List<MovementResponse>.toMovements() = map { it.toMovement() }

private fun MovementResponse.toMovement() = Movement(
        title = title.orEmpty(),
        description = description.orEmpty(),
        amount = amount.orEmpty(),
        category = category.orEmpty(),
        imageUrl = imageUrl.orEmpty(),
        dateShort = dateShort.orEmpty(),
        dateLong = dateLong.orEmpty(),
        time = time.orEmpty(),
        isPositive = isPositive ?: false)
