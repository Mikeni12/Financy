package mx.mikeni.home

import androidx.compose.ui.graphics.Color

data class MovementUi(val title: String,
                      val description: String,
                      val category: String,
                      val imageUrl: String,
                      val dateShort: String,
                      val dateLong: String,
                      val time: String,
                      val amount: String,
                      val amountColor: Color)
