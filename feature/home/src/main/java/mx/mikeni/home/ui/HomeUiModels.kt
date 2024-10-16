package mx.mikeni.home.ui

import androidx.compose.ui.graphics.Color
import mx.mikeni.data.users.Movement
import mx.mikeni.data.users.User

data class HomeUiModel(val showProgress: Boolean = false,
                       val userUi: UserUi? = null,
                       val error: Throwable? = null)

data class UserUi(val name: String,
                  val lastName: String,
                  val email: String,
                  val photoId: String,
                  val movementUiList: List<MovementUi>)

data class MovementUi(val title: String,
                      val description: String,
                      val category: String,
                      val imageUrl: String,
                      val dateShort: String,
                      val dateLong: String,
                      val time: String,
                      val amount: String,
                      val amountColor: Color)

fun User.toUserUi() = UserUi(
        name = name,
        lastName = lastName,
        email = email,
        photoId = photoId,
        movementUiList = movements.toMovementUiList())

private fun List<Movement>.toMovementUiList() = map { it.toMovementUi() }

private fun Movement.toMovementUi() = MovementUi(
        title = title,
        description = description,
        category = category,
        imageUrl = imageUrl,
        dateShort = dateShort,
        dateLong = dateLong,
        time = time,
        amount = amount,
        amountColor = if (isPositive) Color.Green else Color.Red)
