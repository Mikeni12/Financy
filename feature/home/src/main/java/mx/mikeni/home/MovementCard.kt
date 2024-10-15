package mx.mikeni.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage
import mx.mikeni.ui.Neutral100
import mx.mikeni.ui.Purple80
import mx.mikeni.ui.Space16
import mx.mikeni.ui.Space32
import mx.mikeni.ui.Space8

@Composable
fun MovementCard(
        movementUi: MovementUi,
        onMovementListener: (MovementUi) -> Unit,
        modifier: Modifier = Modifier
) {
    Card(
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            onClick = { onMovementListener(movementUi) },
            modifier = modifier
    ) {
        Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(Space16),
                modifier = Modifier.padding(Space8)
        ) {
            MovementCardImage(movementUi.imageUrl)
            MovementCardDetails(movementUi)
        }
    }
}

@Composable
private fun MovementCardImage(
        imageUrl: String,
        modifier: Modifier = Modifier
) {
    Box(
            modifier = Modifier.background(color = Purple80, shape = RoundedCornerShape(Space8))
    ) {
        AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = modifier.size(Space32)
        )
    }
}

@Composable
private fun RowScope.MovementCardDetails(
        movementUi: MovementUi,
        modifier: Modifier = Modifier
) {
    Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.weight(weight = MovementCardDetailsWeight, fill = true)
    ) {
        Column(
                horizontalAlignment = Alignment.Start
        ) {
            Text(
                    text = movementUi.title,
                    style = MaterialTheme.typography.titleMedium,
            )
            Text(
                    text = movementUi.description,
                    color = Neutral100
            )
        }
        Column(
                horizontalAlignment = Alignment.End
        ) {
            Text(
                    text = movementUi.amount,
                    color = movementUi.amountColor,
                    style = MaterialTheme.typography.titleMedium,
            )
            Text(
                    text = movementUi.dateShort,
                    color = Neutral100
            )
        }
    }
}

private const val MovementCardDetailsWeight = 1f
