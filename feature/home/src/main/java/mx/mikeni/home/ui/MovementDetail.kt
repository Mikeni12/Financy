package mx.mikeni.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import mx.mikeni.ui.FinancyTheme
import mx.mikeni.ui.Purple40
import mx.mikeni.ui.Space128
import mx.mikeni.ui.Space16
import mx.mikeni.ui.Space4
import mx.mikeni.ui.Space64

@Composable
fun MovementDetail(
        movementUi: MovementUi,
        modifier: Modifier = Modifier
) {
    Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(Space4),
            horizontalAlignment = Alignment.Start
    ) {
        AsyncImage(
                model = movementUi.imageUrl,
                contentDescription = null,
                modifier = Modifier
                        .size(Space128)
                        .align(alignment = Alignment.CenterHorizontally)
                        .background(color = Purple40, shape = CircleShape)
        )
        Text(
                text = movementUi.title,
                style = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Text(
                text = movementUi.amount,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Text(
                text = "Date",
                style = MaterialTheme.typography.bodyMedium
        )
        Text(
                text = movementUi.dateLong,
                style = MaterialTheme.typography.titleMedium
        )
        Text(
                text = "Time",
                style = MaterialTheme.typography.bodyMedium
        )
        Text(
                text = movementUi.time,
                style = MaterialTheme.typography.titleMedium
        )
        Text(
                text = "Category",
                style = MaterialTheme.typography.bodyMedium
        )
        Text(
                text = movementUi.category,
                style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(Space16))
        Button(
                onClick = { },
                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        ) {
            Icon(
                    imageVector = Icons.Filled.Warning,
                    contentDescription = null
            )
            Text(text = "Report movement")
        }
        Spacer(modifier = Modifier.height(Space64))
    }
}

@Preview
@Composable
private fun MovementDetailPreview() {
    FinancyTheme {
        MovementDetail(
                movementUi = givenMovementUi()
        )
    }
}
