package com.example.pablomolina_proyecto1.modelo

import android.telephony.CarrierConfigManager.Gps
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.pablomolina_proyecto1.R

data class Coche (
    val combustible : Int,
    override val gps: Boolean,
    override val stringResourceId: Int = R.string.coche,
    override val imagenResourceId: Int = R.drawable.coche
): Vehiculo