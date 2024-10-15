package mx.mikeni.data.users

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest
import mx.mikeni.data.ANY_USER_ID
import mx.mikeni.data.givenUser
import mx.mikeni.data.givenUserMap
import mx.mikeni.data.givenUserResponse
import mx.mikeni.data.users.UsersRemoteDataSource.Companion.USERS_COLLECTION
import org.junit.Before
import org.junit.Test

class UsersRemoteDataSourceShould {

    @MockK
    private val firebaseFirestore = mockk<FirebaseFirestore>()

    @MockK
    private val firebaseStorage = mockk<FirebaseStorage>()

    @InjectMockKs
    private var usersRemoteDataSource = UsersRemoteDataSource(firebaseFirestore, firebaseStorage)

    @Before
    fun setUp() = MockKAnnotations.init(this, true)

    @Test
    fun `Return User when getUser is called`() = runTest {
        val userResponse = givenUserResponse()
        val user = givenUser()
        val documentSnapshot = mockk<DocumentSnapshot> { every { data } returns givenUserMap() }
        every { firebaseFirestore.collection(USERS_COLLECTION).document(ANY_USER_ID).get() } returns mockTask(documentSnapshot)
        every { documentSnapshot.toObject(UserResponse::class.java) } returns userResponse

        val result = usersRemoteDataSource.getUser(ANY_USER_ID).getOrNull()

        verify { firebaseFirestore.collection(USERS_COLLECTION).document(ANY_USER_ID).get() }
        assertEquals(user, result)
    }

    @Test
    fun `Return error when getUser is called`() = runTest {
        val exception = Exception()
        every { firebaseFirestore.collection(USERS_COLLECTION).document(ANY_USER_ID).get() } returns mockTask(mockk(), exception)

        val result = usersRemoteDataSource.getUser(ANY_USER_ID).exceptionOrNull()

        verify { firebaseFirestore.collection(USERS_COLLECTION).document(ANY_USER_ID).get() }
        assertEquals(exception, result)
    }

    private fun mockTask(documentSnapshot: DocumentSnapshot, exception: Exception? = null): Task<DocumentSnapshot> {
        val task = mockk<Task<DocumentSnapshot>>(relaxed = true)
        every { task.isComplete } returns true
        every { task.exception } returns exception
        every { task.isCanceled } returns false
        every { task.result } returns documentSnapshot
        return task
    }
}
