package mx.mikeni.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import mx.mikeni.ui.Space16

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
        modifier: Modifier = Modifier,
        onSignOutListener: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val movementUiList = givenExpandedMovementUiList()
    var selectedMovementUiIndex by rememberSaveable { mutableIntStateOf(InitialSelectedMovementUiIndex) }
    Scaffold(
            topBar = {
                TopAppBar(
                        title = {
                            Text("Expenses")
                        },
                        actions = {
                            IconButton(onClick = onSignOutListener) {
                                Icon(
                                        imageVector = Icons.AutoMirrored.Filled.Logout,
                                        contentDescription = null
                                )
                            }
                        }
                )
            },
            modifier = modifier
    ) { paddingValues ->
        MovementList(
                movementUiList = movementUiList,
                onMovementListener = { index ->
                    selectedMovementUiIndex = index
                    showBottomSheet = true
                },
                modifier = Modifier.padding(paddingValues)
        )
    }
    selectedMovementUiIndex.takeIf { it != InitialSelectedMovementUiIndex }?.let {
        if (showBottomSheet) {
            ModalBottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion { if (!sheetState.isVisible) showBottomSheet = false }
                    }
            ) {
                MovementDetail(
                        movementUi = movementUiList[it],
                        modifier = Modifier.padding(horizontal = Space16)
                )
            }
        }
    }
}

private const val InitialSelectedMovementUiIndex = -1
