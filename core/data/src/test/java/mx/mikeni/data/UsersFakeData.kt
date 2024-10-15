package mx.mikeni.data

import mx.mikeni.data.users.Movement
import mx.mikeni.data.users.MovementResponse
import mx.mikeni.data.users.User
import mx.mikeni.data.users.UserResponse

const val ANY_USER_ID = "fOgVo4qWUiRaTjSBGQ9igumIU7o1"
const val ANY_USER_EMAIL = "miguel.alpizar@financy.mx"
const val ANY_USER_PASSWORD = "Password123!"
private const val ANY_USER_NAME = "Miguel"
private const val ANY_USER_LAST_NAME = "Alpizar"
private const val ANY_USER_PHOTO_ID = "photoId"

private const val ANY_MOVEMENT_TITLE = "Received cashback"
private const val ANY_MOVEMENT_DESCRIPTION = "Transfered Money"
private const val ANY_MOVEMENT_CATEGORY = "Income"
private const val ANY_MOVEMENT_IMAGE_URL = "https://icons.veryicon.com/png/o/business/a-set-of-commercial-icons/money-transfer.png"
private const val ANY_MOVEMENT_DATE_SHORT = "11 OCT"
private const val ANY_MOVEMENT_DATE_LONG = "2023-01-01"
private const val ANY_MOVEMENT_TIME = "20:00"
private const val ANY_MOVEMENT_AMOUNT = "+$1000.00"
private const val ANY_MOVEMENT_IS_POSITIVE = true

fun givenUser() = User(
        id = ANY_USER_ID,
        name = ANY_USER_NAME,
        lastName = ANY_USER_LAST_NAME,
        email = ANY_USER_EMAIL,
        photoId = ANY_USER_PHOTO_ID,
        movements = listOf(givenMovement()))

private fun givenMovement() = Movement(
        title = ANY_MOVEMENT_TITLE,
        description = ANY_MOVEMENT_DESCRIPTION,
        category = ANY_MOVEMENT_CATEGORY,
        imageUrl = ANY_MOVEMENT_IMAGE_URL,
        dateShort = ANY_MOVEMENT_DATE_SHORT,
        dateLong = ANY_MOVEMENT_DATE_LONG,
        time = ANY_MOVEMENT_TIME,
        amount = ANY_MOVEMENT_AMOUNT,
        isPositive = ANY_MOVEMENT_IS_POSITIVE)

fun givenUserMap() = mapOf(
        "name" to ANY_USER_NAME,
        "lastName" to ANY_USER_LAST_NAME,
        "email" to ANY_USER_EMAIL,
        "photoId" to ANY_USER_PHOTO_ID,
        "movements" to listOf(givenMovementMap()))

private fun givenMovementMap() = mapOf(
        "title" to ANY_MOVEMENT_TITLE,
        "description" to ANY_MOVEMENT_DESCRIPTION,
        "category" to ANY_MOVEMENT_CATEGORY,
        "imageUrl" to ANY_MOVEMENT_IMAGE_URL,
        "dateShort" to ANY_MOVEMENT_DATE_SHORT,
        "dateLong" to ANY_MOVEMENT_DATE_LONG,
        "time" to ANY_MOVEMENT_TIME,
        "amount" to ANY_MOVEMENT_AMOUNT,
        "isPositive" to ANY_MOVEMENT_IS_POSITIVE)

internal fun givenUserResponse() = UserResponse(
        name = ANY_USER_NAME,
        lastName = ANY_USER_LAST_NAME,
        email = ANY_USER_EMAIL,
        photoId = ANY_USER_PHOTO_ID,
        movements = listOf(givenMovementResponse()))

private fun givenMovementResponse() = MovementResponse(
        title = ANY_MOVEMENT_TITLE,
        description = ANY_MOVEMENT_DESCRIPTION,
        category = ANY_MOVEMENT_CATEGORY,
        imageUrl = ANY_MOVEMENT_IMAGE_URL,
        dateShort = ANY_MOVEMENT_DATE_SHORT,
        dateLong = ANY_MOVEMENT_DATE_LONG,
        time = ANY_MOVEMENT_TIME,
        amount = ANY_MOVEMENT_AMOUNT,
        isPositive = ANY_MOVEMENT_IS_POSITIVE)
