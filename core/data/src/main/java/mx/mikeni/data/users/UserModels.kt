package mx.mikeni.data.users

data class User(val id: String,
                val name: String,
                val email: String,
                val photoId: String,
                val movements: List<Movement>)

data class Movement(val title: String,
                    val description: String,
                    val amount: String,
                    val category: String,
                    val imageUrl: String,
                    val dateShort: String,
                    val dateLong: String,
                    val time: String,
                    val isPositive: String)
