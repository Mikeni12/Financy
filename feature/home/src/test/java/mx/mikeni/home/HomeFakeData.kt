package mx.mikeni.home

import mx.mikeni.ANY_USER_EMAIL
import mx.mikeni.ANY_USER_ID
import mx.mikeni.ANY_USER_LAST_NAME
import mx.mikeni.ANY_USER_NAME
import mx.mikeni.ANY_USER_PHOTO_ID
import mx.mikeni.data.users.User
import mx.mikeni.home.ui.UserUi

fun givenUserUi() = UserUi(
        name = ANY_USER_NAME,
        lastName = ANY_USER_LAST_NAME,
        email = ANY_USER_EMAIL,
        movementUiList = listOf())

fun givenUser() = User(
        id = ANY_USER_ID,
        name = ANY_USER_NAME,
        lastName = ANY_USER_LAST_NAME,
        email = ANY_USER_EMAIL,
        photoId = ANY_USER_PHOTO_ID,
        movements = listOf())
