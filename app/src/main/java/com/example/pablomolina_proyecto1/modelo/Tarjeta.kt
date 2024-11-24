package com.example.pablomolina_proyecto1.modelo

data class Tarjeta (
    val tipoTarjeta: String,
    val numTarjeta : String,
    val fec_vencimiento : String,
    val cvc : Int
)