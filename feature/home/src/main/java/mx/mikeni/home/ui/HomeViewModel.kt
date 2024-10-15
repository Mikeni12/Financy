package mx.mikeni.home.ui

import androidx.lifecycle.ViewModel
import mx.mikeni.home.domain.IGetUserUseCase

class HomeViewModel(private val getUserUseCase: IGetUserUseCase) : ViewModel() {
}
