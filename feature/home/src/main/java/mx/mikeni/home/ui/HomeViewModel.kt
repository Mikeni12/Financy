package mx.mikeni.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.mikeni.home.domain.IGetUserUseCase

class HomeViewModel(private val getUserUseCase: IGetUserUseCase) : ViewModel() {

    private val _homeUiModel = MutableStateFlow(HomeUiModel())

    val homeUiModel: StateFlow<HomeUiModel>
        get() = _homeUiModel

    fun getUser(userId: String) {
        emitHomeUiState(showProgress = true)
        viewModelScope.launch(Dispatchers.IO) {
            val result = getUserUseCase.getUser(userId)
            withContext(Dispatchers.Main) {
                result.onSuccess { getUserSuccess(it.toUserUi()) }
                        .onFailure { getUserFailure(it) }
            }
        }
    }

    private fun getUserSuccess(userUi: UserUi) {
        emitHomeUiState(userUi = userUi)
    }

    private fun getUserFailure(throwable: Throwable) {
        emitHomeUiState(throwable = throwable)
    }

    private fun emitHomeUiState(showProgress: Boolean = false, userUi: UserUi? = null, throwable: Throwable? = null) {
        _homeUiModel.update { HomeUiModel(showProgress, userUi, throwable) }
    }
}
