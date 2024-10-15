package mx.mikeni.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import kotlinx.coroutines.launch
import mx.mikeni.ui.Space16
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
        modifier: Modifier = Modifier,
        viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
        onSignOutListener: () -> Unit
) {
    val homeUiModel by viewModel.homeUiModel.collectAsState()
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    var selectedMovementUiIndex by rememberSaveable { mutableIntStateOf(InitialSelectedMovementUiIndex) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    LaunchedEffect(true) {
        viewModel.getUser("SkBBdUQwDGRfsnLxeTIXIFjqVBH2")
    }

    Scaffold(
            topBar = {
                LargeTopAppBar(
                        title = {
                            if (scrollBehavior.state.collapsedFraction > 0.5f) {
                                Text("Explore your finances today!")
                            } else {
                                homeUiModel.userUi?.let {
                                    Column(verticalArrangement = Arrangement.spacedBy(Space16)) {
                                        Text(
                                                text = "Welcome back, ${it.name} ${it.lastName}!".trim(),
                                                style = MaterialTheme.typography.titleLarge
                                        )
                                        Text(
                                                text = it.email,
                                                style = MaterialTheme.typography.titleMedium
                                        )
                                    }
                                }
                            }
                        },
                        actions = {
                            IconButton(onClick = onSignOutListener) {
                                Icon(
                                        imageVector = Icons.AutoMirrored.Filled.Logout,
                                        contentDescription = null
                                )
                            }
                        },
                        scrollBehavior = scrollBehavior
                )
            },
            modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { paddingValues ->
        HomeContent(
                homeUiModel = homeUiModel,
                modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
        ) {
            selectedMovementUiIndex = it
            showBottomSheet = true
        }
    }

    homeUiModel.userUi?.movementUiList?.getOrNull(selectedMovementUiIndex)?.let {
        if (showBottomSheet) {
            ModalBottomSheet(
                    sheetState = sheetState,
                    onDismissRequest = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion { if (!sheetState.isVisible) showBottomSheet = false }
                    }
            ) {
                MovementDetail(
                        movementUi = it,
                        modifier = Modifier.padding(horizontal = Space16)
                )
            }
        }
    }
}

@Composable
private fun HomeContent(
        homeUiModel: HomeUiModel,
        modifier: Modifier = Modifier,
        onMovementListener: (Int) -> Unit
) {
    with(homeUiModel) {
        Box(modifier = modifier) {
            when {
                showProgress -> {
                    CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                    )
                }

                userUi != null -> {
                    MovementList(
                            movementUiList = userUi.movementUiList,
                            onMovementListener = onMovementListener,
                    )
                }

                error != null -> {

                }
            }
        }
    }
}

private const val InitialSelectedMovementUiIndex = -1
