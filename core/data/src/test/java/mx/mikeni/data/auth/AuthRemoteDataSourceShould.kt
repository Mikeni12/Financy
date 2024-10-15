package mx.mikeni.data.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlinx.coroutines.test.runTest
import mx.mikeni.data.ANY_USER_EMAIL
import mx.mikeni.data.ANY_USER_ID
import mx.mikeni.data.ANY_USER_PASSWORD
import org.junit.Before
import org.junit.Test

class AuthRemoteDataSourceShould {

    @MockK
    private val firebaseAuth = mockk<FirebaseAuth>()

    @InjectMockKs
    private var authRemoteDataSource = AuthRemoteDataSource(firebaseAuth)

    @Before
    fun setUp() = MockKAnnotations.init(this, true)

    @Test
    fun `Return User id when signIn is called`() = runTest {
        val authResult = mockk<AuthResult> { every { user?.uid } returns ANY_USER_ID }
        every { firebaseAuth.signInWithEmailAndPassword(ANY_USER_EMAIL, ANY_USER_PASSWORD) } returns mockTask(authResult)

        val result = authRemoteDataSource.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD).getOrNull()

        verify { firebaseAuth.signInWithEmailAndPassword(ANY_USER_EMAIL, ANY_USER_PASSWORD) }
        assertEquals(ANY_USER_ID, result)
    }

    @Test
    fun `Return User Not Found when signIn is called`() = runTest {
        val authResult = mockk<AuthResult> { every { user } returns null }
        every { firebaseAuth.signInWithEmailAndPassword(ANY_USER_EMAIL, ANY_USER_PASSWORD) } returns mockTask(authResult)

        val result = authRemoteDataSource.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD).exceptionOrNull()

        verify { firebaseAuth.signInWithEmailAndPassword(ANY_USER_EMAIL, ANY_USER_PASSWORD) }
        assertIs<AuthException.UserNotFoundException>(result)
    }

    @Test
    fun `Return Sign In Exception when signIn is called`() = runTest {
        val exception = Exception()
        every { firebaseAuth.signInWithEmailAndPassword(ANY_USER_EMAIL, ANY_USER_PASSWORD) } returns mockTask(mockk(), exception)

        val result = authRemoteDataSource.signIn(ANY_USER_EMAIL, ANY_USER_PASSWORD).exceptionOrNull()

        verify { firebaseAuth.signInWithEmailAndPassword(ANY_USER_EMAIL, ANY_USER_PASSWORD) }
        assertIs<AuthException.SignInException>(result)
    }

    private fun mockTask(authResult: AuthResult, exception: Exception? = null): Task<AuthResult> {
        val task = mockk<Task<AuthResult>>(relaxed = true)
        every { task.isComplete } returns true
        every { task.exception } returns exception
        every { task.isCanceled } returns false
        every { task.result } returns authResult
        return task
    }
}
