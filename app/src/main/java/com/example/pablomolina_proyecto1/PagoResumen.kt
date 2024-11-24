package com.example.pablomolina_proyecto1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pablomolina_proyecto1.modelo.AppUIState
import com.example.pablomolina_proyecto1.modelo.Pedido
import com.example.pablomolina_proyecto1.modelo.Tarjeta
import com.example.pablomolina_proyecto1.ui.theme.PabloMolina_Proyecto1Theme

@Composable
fun PagoResumenPrincipal(modifier: Modifier = Modifier, onConfirmarPago: (Pedido) -> Unit, appUIState: AppUIState){
    val tarjeta = appUIState.tarjeta
    val pedido = appUIState.pedido
    Column(modifier.fillMaxWidth().padding(vertical = 100.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(R.string.confirma_el_pago))
        tarjetaResumenPago(tarjeta!!, pedido!!)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(16.dp)
            .padding(bottom = 100.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center){

        Button(onClick = {onConfirmarPago(pedido!!)
            Intent(Intent.ACTION_SEND).apply {
                // The intent does not have a URI, so declare the "text/plain" MIME type
                type = "text/plain"
                putExtra(Intent.EXTRA_EMAIL, arrayOf("lorenaipsum@dolorsitam.es")) // recipients
                putExtra(Intent.EXTRA_SUBJECT, "SU VEHICULO ALQUILADO")
                putExtra(Intent.EXTRA_TEXT, "Ha alquilado un vehiculo con exito")
                // You can also attach multiple items by passing an ArrayList of Uris
            }
                         }, modifier = Modifier.padding(20.dp)) {
            Text(text = stringResource(R.string.aceptar))
        }
    }
}

@Composable
fun tarjetaResumenPago(tarjeta: Tarjeta, pedido: Pedido){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 250.dp, horizontal = 100.dp)) {
            Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = tarjeta.tipoTarjeta
                )
                Text(
                    text = tarjeta.numTarjeta
                )
                Text(
                    text = pedido.precio.toString() + "€"
                )
            }

    }
}