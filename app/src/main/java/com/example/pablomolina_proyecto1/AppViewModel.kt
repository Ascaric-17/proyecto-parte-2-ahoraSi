package com.example.pablomolina_proyecto1

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.pablomolina_proyecto1.modelo.AppUIState
import com.example.pablomolina_proyecto1.modelo.Pedido
import com.example.pablomolina_proyecto1.modelo.Tarjeta
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class AppViewModel: ViewModel() {
    private val _appUIState = MutableStateFlow(AppUIState())
    val appUIState: StateFlow<AppUIState> = _appUIState.asStateFlow()

    var pedidoMutable by mutableStateOf(Pedido(null,0.0,0))
        private set

    var tarjetaMutable by  mutableStateOf(Tarjeta("", "", "", 0))
        private set

    fun actualizarPedido(nuevoPedido: Pedido) {
        pedidoMutable = nuevoPedido

        _appUIState.update { estadoactual ->
            estadoactual.copy(
                pedido = pedidoMutable
            )
        }
    }

    fun insertarPedido(pedido: Pedido){
        _appUIState.update { estadoactual ->
            estadoactual.copy(
                pedidos = estadoactual.pedidos.plus(pedido)
            )
        }
    }

    fun actualizarTarjeta(nuevaTarjeta: Tarjeta) {
        tarjetaMutable = nuevaTarjeta

        _appUIState.update { estadoactual ->
            estadoactual.copy(
                tarjeta = tarjetaMutable
            )
        }
    }
}