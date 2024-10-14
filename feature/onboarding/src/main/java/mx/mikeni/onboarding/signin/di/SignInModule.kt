package mx.mikeni.onboarding.signin.di

import mx.mikeni.data.auth.AuthRemoteDataSource
import mx.mikeni.data.auth.IAuthRemoteDataSource
import mx.mikeni.onboarding.signin.ui.SignInViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val signInModule = module {
    singleOf(::AuthRemoteDataSource).bind<IAuthRemoteDataSource>()
    viewModelOf(::SignInViewModel)
}
