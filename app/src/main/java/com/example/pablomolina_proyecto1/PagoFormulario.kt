package com.example.pablomolina_proyecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.pablomolina_proyecto1.modelo.AppUIState
import com.example.pablomolina_proyecto1.modelo.Tarjeta
import com.example.pablomolina_proyecto1.ui.theme.PabloMolina_Proyecto1Theme

@Composable
fun PagoPrincipal(modifier: Modifier = Modifier, onAceptarPago: (Tarjeta) -> Unit){
    var tipoTarjeta by remember { mutableStateOf("VISA") }
    var numeroTarjeta by remember { mutableStateOf("") }
    var fechaTarjeta by remember { mutableStateOf("") }
    var cvcTarjeta by remember { mutableStateOf("") }

    Column {
        val listaTiposTarjeta = listOf(stringResource(R.string.visa),
            stringResource(R.string.mastercard), stringResource(R.string.euro_6000))

        var (eleccionTarjeta, onTarjetaElegida) = remember { mutableStateOf(listaTiposTarjeta[0]) }

        Column(modifier = Modifier
            .selectableGroup()
            .fillMaxWidth()
            .padding(horizontal = 40.dp, vertical = 100.dp)){
            listaTiposTarjeta.forEach { opcion ->
                Row (verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.selectable(
                        selected = (opcion == eleccionTarjeta),
                        onClick = {
                            eleccionTarjeta = opcion
                            onTarjetaElegida(opcion)
                            tipoTarjeta = opcion
                        }
                    )){


                    RadioButton(selected = (opcion == eleccionTarjeta),
                        onClick = {
                            eleccionTarjeta = opcion
                            onTarjetaElegida(opcion)
                            tipoTarjeta = opcion
                        })
                    Text(text = opcion)
                }
            }
        }
        informacionTarjeta(numeroTarjeta, fechaTarjeta, cvcTarjeta,
            onValueChange1 = {numeroTarjeta = it},
            onValueChange2 = {fechaTarjeta = it},
            onValueChange3 = {cvcTarjeta = it})
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(16.dp)
            .padding(bottom = 100.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center){


        Button(onClick = {
            if(numeroTarjeta.isNotEmpty() && fechaTarjeta.isNotEmpty() && cvcTarjeta.isNotEmpty()){
                val tarjeta = Tarjeta(tipoTarjeta, numeroTarjeta, fechaTarjeta, cvcTarjeta.toInt())
                onAceptarPago(tarjeta!!)
            }}, modifier = Modifier.padding(20.dp)) {

            Text(text = stringResource(R.string.aceptar))
        }
    }
}

/*
@Composable
fun tipoTarjeta(){
    val listaTiposTarjeta = listOf(stringResource(R.string.visa),
        stringResource(R.string.mastercard), stringResource(R.string.euro_6000))

    var (eleccionTarjeta, onTarjetaElegida) = remember { mutableStateOf(listaTiposTarjeta[0]) }

    Column(modifier = Modifier
        .selectableGroup()
        .fillMaxWidth()
        .padding(horizontal = 40.dp, vertical = 100.dp)){
        listaTiposTarjeta.forEach { opcion ->
            Row (verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.selectable(
                    selected = (opcion == eleccionTarjeta),
                    onClick = {
                        eleccionTarjeta = opcion
                        onTarjetaElegida(opcion)
                    }
                )){


            RadioButton(selected = (opcion == eleccionTarjeta),
                    onClick = {
                    eleccionTarjeta = opcion
                    onTarjetaElegida(opcion)
                    })
                Text(text = opcion)
            }
        }
    }
}
*/


@Composable
fun informacionTarjeta(
    value1: String, value2: String, value3: String, onValueChange1: (String) -> Unit,
    modifier: Modifier = Modifier,
    onValueChange2: (String) -> Unit,
    onValueChange3: (String) -> Unit
){

    Column(modifier = Modifier.padding(40.dp)){
        TextField(
            value = value1,
            onValueChange = {
                if (it.length <= 16) {
                    onValueChange1(it)
                }
            },
            singleLine = true,
            label = { Text(text = stringResource(R.string.n_mero_tarjeta))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = modifier
        )

        TextField(
            value = value2,
            onValueChange = {
                if (it.length <= 4) {
                    onValueChange2(it)
                }
            },
            singleLine = true,
            label = { Text(text = stringResource(R.string.fecha_de_caducidad))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = modifier.padding(vertical = 40.dp)
        )

        TextField(
            value = value3,
            onValueChange = {
                if (it.length <= 3) {
                    onValueChange3(it)
                }
            },
            singleLine = true,
            label = { Text(text = stringResource(R.string.cvc))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = modifier
        )
    }
}
