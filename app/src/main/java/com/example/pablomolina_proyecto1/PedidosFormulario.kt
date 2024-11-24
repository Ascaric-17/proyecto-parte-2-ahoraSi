package com.example.pablomolina_proyecto1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
import com.example.pablomolina_proyecto1.modelo.Coche
import com.example.pablomolina_proyecto1.modelo.Moto
import com.example.pablomolina_proyecto1.modelo.Patin
import com.example.pablomolina_proyecto1.modelo.Pedido
import com.example.pablomolina_proyecto1.modelo.Vehiculo
import java.text.NumberFormat

@Composable
fun FormularioMostrar(modifier: Modifier = Modifier.padding(16.dp), onBotonResumenPedido: (Pedido) -> Unit){

    var errorDias by remember { mutableStateOf(false) }

    var pedido: Pedido? = null
    var vehiculo: Vehiculo? = null
    val listaTiposVehiculos = listOf(R.string.coche, R.string.moto, R.string.patin)
    val listaCombustibles = listOf(R.string.gasolina, R.string.diesel, R.string.electrico)
    val listaCilindradas = listOf(R.string._50cc, R.string._125cc, R.string._250cc)

    var tieneGps by remember { mutableStateOf(false) }

    var (eleccionTipoVehiculo, onTipoElegido) = remember { mutableStateOf(listaTiposVehiculos[0]) }
    var tipoVehiculo by remember { mutableStateOf(eleccionTipoVehiculo.toInt())}

    var (eleccionCombustible, onCombustibleElegido) = remember { mutableStateOf(listaCombustibles[0]) }
    var tipoCombustible by remember { mutableStateOf(eleccionCombustible.toInt())}

    var (eleccionCilindrada, onCilindradaElegida) = remember { mutableStateOf(listaCilindradas[0]) }
    var tipoCilindrada by remember { mutableStateOf(eleccionCilindrada.toInt())}

    var diasInput by remember { mutableStateOf("") }

    val precioTotal = calcularPrecio(diasInput, tieneGps, tipoVehiculo, tipoCombustible, tipoCilindrada)

    modifier.padding(top = 40.dp)

    Column(modifier = Modifier
        .padding(horizontal = 40.dp)
        .statusBarsPadding()
        .fillMaxWidth(),){
        // PRIMERA FILA DEL FORMULARIO
        Row (
            Modifier
                .selectableGroup()
                .padding(vertical = 50.dp)
                .align(Alignment.CenterHorizontally)){
            listaTiposVehiculos.forEach { tipo ->
                Row (
                    modifier = Modifier.selectable(
                        selected = (eleccionTipoVehiculo == tipo),
                        onClick = {
                            eleccionTipoVehiculo = tipo
                            onTipoElegido(tipo)
                            tipoVehiculo = eleccionTipoVehiculo.toInt()
                        }
                    ), verticalAlignment = Alignment.CenterVertically){
                    RadioButton(selected = (tipo == eleccionTipoVehiculo),
                        onClick = {
                            eleccionTipoVehiculo = tipo
                            onTipoElegido(tipo)
                            tipoVehiculo = eleccionTipoVehiculo.toInt()})
                    Text(text = stringResource(tipo))
                }
            }
        }
        // SEGUNDA FILA DEL FORMULARIO
        if(tipoVehiculo == R.string.coche){
            Row (
                Modifier
                    .selectableGroup()
                    .padding(vertical = 50.dp)
                    .align(Alignment.CenterHorizontally)){
                listaCombustibles.forEach { combustible ->
                    Row (
                        modifier = Modifier.selectable(
                            selected = (eleccionCombustible == combustible),
                            onClick = {
                                eleccionCombustible = combustible
                                onCombustibleElegido(combustible)
                                tipoCombustible = eleccionCombustible.toInt()
                            }
                        ), verticalAlignment = Alignment.CenterVertically){
                        RadioButton(selected = (combustible == eleccionCombustible),
                            onClick = {
                                eleccionCombustible = combustible
                                onCombustibleElegido(combustible)
                                tipoCombustible = eleccionCombustible.toInt()})
                        Text(text = stringResource(combustible))
                    }
                }
            }
            vehiculo = Coche(tipoCombustible, tieneGps)
        } else if(tipoVehiculo == R.string.moto){
            Row (
                Modifier
                    .selectableGroup()
                    .padding(vertical = 50.dp)){
                listaCilindradas.forEach { cilindrada ->
                    Row (
                        modifier = Modifier.selectableGroup().selectable(
                            selected = (eleccionCilindrada == cilindrada),
                            onClick = {
                                eleccionCilindrada = cilindrada
                                onCilindradaElegida(cilindrada)
                                tipoCilindrada = eleccionCilindrada.toInt()
                            }
                        ), verticalAlignment = Alignment.CenterVertically){
                        RadioButton(selected = (cilindrada == eleccionCilindrada),
                            onClick = {
                                eleccionCilindrada = cilindrada
                                onCilindradaElegida(cilindrada)
                                tipoCilindrada = eleccionCilindrada.toInt()})
                        Text(text = stringResource(cilindrada))
                    }
                }
            }
            vehiculo = Moto(tipoCilindrada, tieneGps)
        } else {
            vehiculo = Patin(tieneGps)
        }
        // TERCERA FILA DEL FORMULARIO
        Row (verticalAlignment = Alignment.CenterVertically){
            Checkbox(checked = tieneGps, onCheckedChange = {tieneGps = it},
                Modifier.clickable {})
            Text(text = stringResource(R.string.gps))

            CampoEdicionDias(
                value = diasInput,
                onValueChange = { diasInput = it },
                modifier = Modifier
                    .padding(start = 30.dp)
                    .weight(1f),
                errorDias
            )
        }

        Text(text = stringResource(R.string.precio, NumberFormat.getCurrencyInstance().format(precioTotal)),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.fillMaxWidth()
                .padding(80.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .padding(16.dp)
                .padding(bottom = 70.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center){
            Button(onClick = {if(diasInput.isNotEmpty()){
                pedido = Pedido(vehiculo!!,precioTotal.toDouble(),diasInput.toInt())
                onBotonResumenPedido(pedido!!)
            } else {
                errorDias = true
            }}, modifier = Modifier.padding(10.dp)) {
                Text(text = stringResource(R.string.aceptar))
            }
        }
    }
}

@Composable
fun CampoEdicionDias(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorDias: Boolean) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(text = stringResource(R.string.dias_a_alquilar))},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier,
        isError = errorDias
    )
}

private fun calcularPrecio(diasInput: String, tieneGps: Boolean,
                           tipoVehiculo: Int, tipoCombustible: Int, tipoCilindrada: Int): Int {

    val dias = diasInput.toIntOrNull() ?: return 0
    var precioPorDia = 0

    // Establezco el precio por tipo de vehículo
    when (tipoVehiculo) {
        R.string.coche -> {
            precioPorDia = when (tipoCombustible) {
                R.string.gasolina -> 20
                R.string.diesel -> 25
                R.string.electrico -> 15
                else -> 0
            }
        }
        R.string.moto -> {
            precioPorDia = when (tipoCilindrada) {
                R.string._50cc -> 10
                R.string._125cc -> 15
                R.string._250cc -> 20
                else -> 0
            }
        }
        R.string.patin -> {
            precioPorDia = 5
        }
    }

    if (tieneGps) {
        precioPorDia += 5
    }

    val precioTotal = precioPorDia * dias

    return precioTotal
}