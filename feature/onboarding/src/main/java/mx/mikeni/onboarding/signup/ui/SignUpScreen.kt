package mx.mikeni.onboarding.signup.ui

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import mx.mikeni.ui.ErrorSnackBar
import mx.mikeni.ui.Pink40
import mx.mikeni.ui.Pink80
import mx.mikeni.ui.Space16
import mx.mikeni.ui.Space32
import mx.mikeni.ui.Space8
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignUpScreen(
        modifier: Modifier = Modifier,
        viewModel: SignUpViewModel = koinViewModel<SignUpViewModel>(),
        onSignUpListener: (String) -> Unit
) {
    val signUpUiModel by viewModel.signUpUiModel.collectAsState()
    var email by remember { mutableStateOf(String()) }
    var password by remember { mutableStateOf(String()) }
    var name by remember { mutableStateOf(String()) }
    var lastName by remember { mutableStateOf(String()) }
    var currentPhotoUri by remember { mutableStateOf(Uri.EMPTY) }
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(
            snackbarHost = {
                SnackbarHost(snackBarHostState) {
                    ErrorSnackBar(message = it.visuals.message)
                }
            },
            modifier = modifier
    ) { paddingValues ->
        SignUpContent(
                email = email,
                password = password,
                name = name,
                lastName = lastName,
                photoUri = currentPhotoUri,
                userId = signUpUiModel.userId,
                coroutineScope = scope,
                onEmailChangedListener = { email = it },
                onPasswordChangedListener = { password = it },
                onNameChangedListener = { name = it },
                onLastNameChangedListener = { lastName = it },
                onTakePhotoListener = { currentPhotoUri = it },
                onSignUpListener = {
                    viewModel.signUp(email, password, name, lastName, currentPhotoUri)
                },
                onHomeListener = {
                    onSignUpListener(signUpUiModel.userId.orEmpty())
                },
                modifier = modifier.padding(paddingValues)
        )
        signUpUiModel.error?.message?.let {
            scope.launch { snackBarHostState.showSnackbar(it) }
        }
    }
}

@Composable
private fun SignUpContent(
        email: String,
        password: String,
        name: String,
        lastName: String,
        photoUri: Uri,
        userId: String?,
        coroutineScope: CoroutineScope,
        onEmailChangedListener: (String) -> Unit,
        onPasswordChangedListener: (String) -> Unit,
        onNameChangedListener: (String) -> Unit,
        onLastNameChangedListener: (String) -> Unit,
        onTakePhotoListener: (Uri) -> Unit,
        onSignUpListener: () -> Unit,
        onHomeListener: () -> Unit,
        modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    if (userId != null) coroutineScope.launch { pagerState.animateScrollToPage(2) }
    Box(
            modifier = modifier.fillMaxSize()
    ) {
        HorizontalPager(
                state = pagerState,
                userScrollEnabled = false
        ) {
            Column(
                    verticalArrangement = Arrangement.spacedBy(Space32),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = Space32, horizontal = Space16)
            ) {
                when (it) {
                    0 -> UserFormScreen(
                            email = email,
                            password = password,
                            name = name,
                            lastName = lastName,
                            onEmailChangedListener = onEmailChangedListener,
                            onPasswordChangedListener = onPasswordChangedListener,
                            onNameChangedListener = onNameChangedListener,
                            onLastNameChangedListener = onLastNameChangedListener,
                            onNextListener = {
                                coroutineScope.launch { pagerState.animateScrollToPage(1) }
                            },
                            modifier = Modifier.fillMaxWidth()
                    )

                    1 -> UserPhotoScreen(
                            photoUri = photoUri,
                            onTakePhotoListener = onTakePhotoListener,
                            onPreviousListener = {
                                coroutineScope.launch { pagerState.animateScrollToPage(0) }
                            },
                            onSignUpListener = {
                                onSignUpListener()
                            }
                    )

                    else -> UserSignedScreen(
                            onHomeListener = onHomeListener
                    )
                }
            }
        }
        Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(bottom = Space32)
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) Pink40 else Pink80
                Box(
                        modifier = Modifier
                                .padding(Space8)
                                .clip(CircleShape)
                                .background(color)
                                .size(Space16)
                )
            }
        }
    }
}
