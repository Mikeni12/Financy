package mx.mikeni.onboarding.signin.di

import mx.mikeni.data.auth.AuthRemoteDataSource
import mx.mikeni.data.auth.IAuthRemoteDataSource
import mx.mikeni.onboarding.signin.data.AuthRepository
import mx.mikeni.onboarding.signin.data.IAuthRepository
import mx.mikeni.onboarding.signin.domain.ISignInUseCase
import mx.mikeni.onboarding.signin.domain.ISignUpUseCase
import mx.mikeni.onboarding.signin.domain.SignInUseCase
import mx.mikeni.onboarding.signin.domain.SignUpUseCase
import mx.mikeni.onboarding.signin.ui.SignInViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val signInModule = module {
    factoryOf(::AuthRemoteDataSource).bind<IAuthRemoteDataSource>()
    factoryOf(::AuthRepository).bind<IAuthRepository>()
    factoryOf(::SignInUseCase).bind<ISignInUseCase>()
    factoryOf(::SignUpUseCase).bind<ISignUpUseCase>()
    viewModelOf(::SignInViewModel)
}
