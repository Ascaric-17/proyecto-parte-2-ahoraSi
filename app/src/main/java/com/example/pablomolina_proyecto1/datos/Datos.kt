package com.example.pablomolina_proyecto1.datos

import com.example.pablomolina_proyecto1.R
import com.example.pablomolina_proyecto1.modelo.Coche
import com.example.pablomolina_proyecto1.modelo.Moto
import com.example.pablomolina_proyecto1.modelo.Patin
import com.example.pablomolina_proyecto1.modelo.Vehiculo

class Datos {
    fun cargarPedidos(): List<Vehiculo> = listOf(
        Coche(R.string.gasolina, true, R.string.coche, R.drawable.coche),
        Moto(R.string._125cc, true, R.string.moto, R.drawable.moto),
        Patin(false, R.string.patin, R.drawable.patin)
    )
}