package mx.mikeni.data.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.mikeni.data.auth.AuthRemoteDataSource
import mx.mikeni.data.auth.IAuthRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val firebaseModule = module {
    single { Firebase.auth }
    single { Firebase.firestore }
    singleOf(::AuthRemoteDataSource).bind<IAuthRemoteDataSource>()
}
