package mx.mikeni.onboarding.di

import mx.mikeni.data.auth.AuthRemoteDataSource
import mx.mikeni.data.auth.AuthRepository
import mx.mikeni.data.auth.IAuthRemoteDataSource
import mx.mikeni.data.auth.IAuthRepository
import mx.mikeni.onboarding.signin.domain.ISignInUseCase
import mx.mikeni.onboarding.signin.domain.SignInUseCase
import mx.mikeni.onboarding.signin.ui.SignInViewModel
import mx.mikeni.onboarding.signup.domain.ISignUpUseCase
import mx.mikeni.onboarding.signup.domain.SignUpUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val onboardingModule = module {
    factoryOf(::AuthRemoteDataSource).bind<IAuthRemoteDataSource>()
    factoryOf(::AuthRepository).bind<IAuthRepository>()
    factoryOf(::SignInUseCase).bind<ISignInUseCase>()
    factoryOf(::SignUpUseCase).bind<ISignUpUseCase>()
    viewModelOf(::SignInViewModel)
}
