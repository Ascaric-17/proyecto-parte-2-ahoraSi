package com.example.pablomolina_proyecto1.modelo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.pablomolina_proyecto1.R

data class Moto (
    val cilindrada : Int,
    override val gps: Boolean,
    override val stringResourceId: Int = R.string.moto,
    override val imagenResourceId: Int = R.drawable.moto
) : Vehiculo