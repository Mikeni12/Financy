package mx.mikeni.onboarding.di

import mx.mikeni.onboarding.signin.domain.ISignInUseCase
import mx.mikeni.onboarding.signin.domain.SignInUseCase
import mx.mikeni.onboarding.signin.ui.SignInViewModel
import mx.mikeni.onboarding.signup.domain.ISignUpUseCase
import mx.mikeni.onboarding.signup.domain.SignUpUseCase
import mx.mikeni.onboarding.signup.ui.SignUpViewModel
import mx.mikeni.ui.CoroutinesDispatchers
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val onboardingModule = module {
    single { CoroutinesDispatchers() }
    factoryOf(::SignInUseCase).bind<ISignInUseCase>()
    factoryOf(::SignUpUseCase).bind<ISignUpUseCase>()
    viewModelOf(::SignInViewModel)
    viewModelOf(::SignUpViewModel)
}
