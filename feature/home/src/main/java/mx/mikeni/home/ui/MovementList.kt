package mx.mikeni.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import mx.mikeni.ui.FinancyTheme
import mx.mikeni.ui.Neutral00
import mx.mikeni.ui.Space16
import mx.mikeni.ui.Space4
import mx.mikeni.ui.toast

@Composable
fun MovementList(
        movementUiList: List<MovementUi>,
        onMovementListener: (Int) -> Unit,
        modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        LazyColumn(
                contentPadding = PaddingValues(horizontal = Space16),
                modifier = Modifier.background(Neutral00).fillMaxWidth()
        ) {
            itemsIndexed(movementUiList) { index, it ->
                MovementCard(
                        movementUi = it,
                        onMovementListener = { onMovementListener(index) },
                        modifier = Modifier.padding(vertical = Space4)
                )
            }
        }
    }
}

@Preview
@Composable
private fun MovementListPreview() {
    FinancyTheme {
        val context = LocalContext.current
        MovementList(
                movementUiList = givenExpandedMovementUiList(),
                onMovementListener = { context.toast("Movement $it clicked") }
        )
    }
}
