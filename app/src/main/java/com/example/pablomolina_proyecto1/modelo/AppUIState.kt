package com.example.pablomolina_proyecto1.modelo

import com.example.pablomolina_proyecto1.R

data class AppUIState(

    val pedido: Pedido? = null,

    val pedidos: List<Pedido> = listOf(
        Pedido(Coche(R.string.gasolina, true, R.string.coche, R.drawable.coche),30.0,1),
        Pedido(Moto(R.string._125cc, true, R.string.moto, R.drawable.moto),60.0,2),
        Pedido(Patin(false, R.string.patin, R.drawable.patin),50.0,3)
    ),

    val tarjeta: Tarjeta? = null

)