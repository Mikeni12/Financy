package mx.mikeni.home

import androidx.compose.ui.graphics.Color

fun givenExpandedMovementUiList() = List(10) { givenMovementUiList() }.flatten()

fun givenMovementUi() = givenMovementUiList().first()

private fun givenMovementUiList() = listOf(
        MovementUi(
                title = "Received cashback",
                description = "Transfered Money",
                category = "Income",
                imageUrl = "https://cdn-icons-png.flaticon.com/512/1801/1801444.png",
                dateShort = "11 OCT",
                dateLong = "2023-01-01",
                time = "20:00",
                amount = "+$1000.00",
                amountColor = Color.Green,
        ),
        MovementUi(
                title = "Uber",
                description = "Taxi Service",
                category = "Transport",
                imageUrl = "https://cdn-icons-png.flaticon.com/512/1801/1801444.png",
                dateShort = "11 OCT",
                dateLong = "2023-01-01",
                time = "20:00",
                amount = "-$30.00",
                amountColor = Color.Red,
        ),
        MovementUi(
                title = "Uber eats",
                description = "Card Payment",
                category = "Food",
                imageUrl = "https://cdn-icons-png.flaticon.com/512/1801/1801444.png",
                dateShort = "11 OCT",
                dateLong = "2023-01-01",
                time = "20:00",
                amount = "-$120.00",
                amountColor = Color.Red,
        ),
        MovementUi(
                title = "Didi Food",
                description = "Card Payment",
                category = "Food",
                imageUrl = "https://cdn-icons-png.flaticon.com/512/1801/1801444.png",
                dateShort = "11 OCT",
                dateLong = "2023-01-01",
                time = "20:00",
                amount = "-$50.00",
                amountColor = Color.Red,
        ),
        MovementUi(
                title = "Didi",
                description = "Taxi Service",
                category = "Transport",
                imageUrl = "https://cdn-icons-png.flaticon.com/512/1801/1801444.png",
                dateShort = "11 OCT",
                dateLong = "2023-01-01",
                time = "20:00",
                amount = "-$140.00",
                amountColor = Color.Red,
        ),
)
