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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import mx.mikeni.ui.Pink40
import mx.mikeni.ui.Pink80
import mx.mikeni.ui.Space16
import mx.mikeni.ui.Space32
import mx.mikeni.ui.Space8

@Composable
fun SignUpScreen(
        modifier: Modifier = Modifier,
        onSignUpListener: () -> Unit
) {
    var email by remember { mutableStateOf("miguel.alpizar@financy.mx") }
    var password by remember { mutableStateOf("Password123!") }
    var name by remember { mutableStateOf("Miguel") }
    var lastName by remember { mutableStateOf("Alpizar") }
    var currentPhotoUri by remember { mutableStateOf(value = Uri.EMPTY) }
    Scaffold(
            modifier = modifier
    ) { paddingValues ->
        SignUpContent(
                email = email,
                password = password,
                name = name,
                lastName = lastName,
                photoUri = currentPhotoUri,
                onEmailChangedListener = { email = it },
                onPasswordChangedListener = { password = it },
                onNameChangedListener = { name = it },
                onLastNameChangedListener = { lastName = it },
                onTakePhotoListener = { currentPhotoUri = it },
                modifier = modifier.padding(paddingValues)
        )
    }
//    Button(
//            onClick = onSignUpListener,
//            modifier = Modifier.wrapContentSize()
//    ) {
//        Text("Sign In")
//    }
}

@Composable
private fun SignUpContent(
        email: String,
        password: String,
        name: String,
        lastName: String,
        photoUri: Uri,
        onEmailChangedListener: (String) -> Unit,
        onPasswordChangedListener: (String) -> Unit,
        onNameChangedListener: (String) -> Unit,
        onLastNameChangedListener: (String) -> Unit,
        onTakePhotoListener: (Uri) -> Unit,
        modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    Box(
            modifier = modifier.fillMaxSize()
    ) {
        HorizontalPager(
                state = pagerState
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
                            modifier = Modifier.fillMaxWidth()
                    )

                    1 -> UserPhotoScreen(
                            photoUri = photoUri,
                            onTakePhotoListener = onTakePhotoListener
                    )

                    else -> UserSignedScreen()
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
