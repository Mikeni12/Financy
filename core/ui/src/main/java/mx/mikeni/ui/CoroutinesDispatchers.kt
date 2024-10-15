package mx.mikeni.ui

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

data class CoroutinesDispatchers(val main: CoroutineDispatcher = Dispatchers.Main,
                                 val io: CoroutineDispatcher = Dispatchers.IO)
