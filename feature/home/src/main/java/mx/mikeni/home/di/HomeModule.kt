package mx.mikeni.home.di

import mx.mikeni.home.domain.GetUserUseCase
import mx.mikeni.home.domain.IGetUserUseCase
import mx.mikeni.home.ui.HomeViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val homeModule = module {
    factoryOf(::GetUserUseCase).bind<IGetUserUseCase>()
    viewModelOf(::HomeViewModel)
}
