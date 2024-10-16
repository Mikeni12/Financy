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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
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
    val signUpFormUiModel by viewModel.signUpFormUiModel.collectAsState()
    val signUpUiModel by viewModel.signUpUiModel.collectAsState()
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
                isEmailError = signUpFormUiModel.isEmailError,
                isPasswordError = signUpFormUiModel.isPasswordError,
                isNameError = signUpFormUiModel.isNameError,
                isLastNameError = signUpFormUiModel.isLastNameError,
                isFormValid = signUpFormUiModel.isFormValid,
                userId = signUpUiModel.userId,
                coroutineScope = scope,
                onFormListener = { email, password, name, lastName ->
                    viewModel.validateForm(email, password, name, lastName)
                },
                onSignUpListener = { email, password, name, lastName, currentPhotoUri ->
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
        isEmailError: Boolean,
        isPasswordError: Boolean,
        isNameError: Boolean,
        isLastNameError: Boolean,
        isFormValid: Boolean,
        userId: String?,
        coroutineScope: CoroutineScope,
        onFormListener: (String, String, String, String) -> Unit,
        onSignUpListener: (String, String, String, String, Uri) -> Unit,
        onHomeListener: () -> Unit,
        modifier: Modifier = Modifier
) {
    var email by remember { mutableStateOf(String()) }
    var password by remember { mutableStateOf(String()) }
    var name by remember { mutableStateOf(String()) }
    var lastName by remember { mutableStateOf(String()) }
    var currentPhotoUri by remember { mutableStateOf(Uri.EMPTY) }
    val pagerState = rememberPagerState(pageCount = { SignUpPagerSize })
    val focusManager = LocalFocusManager.current

    LaunchedEffect(isFormValid) {
        if (isFormValid) {
            coroutineScope.launch { pagerState.animateScrollToPage(UserPhotoIndex) }
        }
    }
    LaunchedEffect(userId) {
        if (userId != null) {
            coroutineScope.launch {
                pagerState.animateScrollToPage(UserSignedIndex)
            }
        }
    }
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
                    UserFormIndex -> UserFormScreen(
                            email = email,
                            password = password,
                            name = name,
                            lastName = lastName,
                            isEmailError = isEmailError,
                            isPasswordError = isPasswordError,
                            isNameError = isNameError,
                            isLastNameError = isLastNameError,
                            onEmailChangedListener = { email = it },
                            onPasswordChangedListener = { password = it },
                            onNameChangedListener = { name = it },
                            onLastNameChangedListener = { lastName = it },
                            onNextListener = {
                                focusManager.clearFocus()
                                if (isFormValid) {
                                    coroutineScope.launch { pagerState.animateScrollToPage(UserPhotoIndex) }
                                } else {
                                    onFormListener(email, password, name, lastName)
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                    )

                    UserPhotoIndex -> UserPhotoScreen(
                            photoUri = currentPhotoUri,
                            onTakePhotoListener = { currentPhotoUri = it },
                            onPreviousListener = {
                                coroutineScope.launch { pagerState.animateScrollToPage(UserFormIndex) }
                            },
                            onSignUpListener = {
                                onSignUpListener(email, password, name, lastName, currentPhotoUri)
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

private const val UserFormIndex = 0
private const val UserPhotoIndex = 1
private const val UserSignedIndex = 2
private const val SignUpPagerSize = 3
