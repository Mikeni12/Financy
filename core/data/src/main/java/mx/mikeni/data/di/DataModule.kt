package mx.mikeni.data.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.mikeni.data.auth.AuthRemoteDataSource
import mx.mikeni.data.auth.AuthRepository
import mx.mikeni.data.auth.IAuthRemoteDataSource
import mx.mikeni.data.auth.IAuthRepository
import mx.mikeni.data.users.IUsersRemoteDataSource
import mx.mikeni.data.users.IUsersRepository
import mx.mikeni.data.users.UsersRemoteDataSource
import mx.mikeni.data.users.UsersRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {
    single { Firebase.auth }
    single { Firebase.firestore }
    singleOf(::AuthRemoteDataSource).bind<IAuthRemoteDataSource>()
    singleOf(::AuthRepository).bind<IAuthRepository>()
    singleOf(::UsersRemoteDataSource).bind<IUsersRemoteDataSource>()
    singleOf(::UsersRepository).bind<IUsersRepository>()
}
