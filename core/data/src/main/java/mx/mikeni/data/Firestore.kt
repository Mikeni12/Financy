package mx.mikeni.data

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class UsersRemoteDataSource(private val firebaseFirestore: FirebaseFirestore) {

    fun signIn(email: String, password: String) : Result<Boolean> {
        val result = firebaseFirestore.collection("users").document(email).get()
                .addOnSuccessListener {
                    Log.d("totopito", "Correcto")
                }
        return Result.success(true)
    }
}
