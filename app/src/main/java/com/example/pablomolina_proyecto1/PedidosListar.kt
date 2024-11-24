package com.example.pablomolina_proyecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pablomolina_proyecto1.datos.Datos
import com.example.pablomolina_proyecto1.modelo.AppUIState
import com.example.pablomolina_proyecto1.modelo.Coche
import com.example.pablomolina_proyecto1.modelo.Moto
import com.example.pablomolina_proyecto1.modelo.Pedido
import com.example.pablomolina_proyecto1.modelo.Vehiculo
import com.example.pablomolina_proyecto1.ui.theme.PabloMolina_Proyecto1Theme

@Composable
fun ListadoPrincipal(modifier: Modifier = Modifier, appUIState: AppUIState) {
    ListaPedidos(lista = appUIState.pedidos, modifier = modifier)

}

@Composable
fun ListaPedidos(lista : List<Pedido>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier){
        items(lista) { pedido ->
            TarjetaVehiculo(
                pedido = pedido,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun TarjetaVehiculo(pedido: Pedido, modifier: Modifier = Modifier){
    Card (modifier = modifier.padding(10.dp),
        onClick = {}){
        Row (modifier = Modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = pedido.vehiculo!!.imagenResourceId),
                contentDescription = stringResource(id = pedido.vehiculo.stringResourceId),
                modifier = Modifier
                    .padding(10.dp)
                    .size(200.dp)
            )
            Column {
                Text(
                    text = stringResource(id = pedido.vehiculo.stringResourceId)
                )

                // Para cada caso se muestra el texto correspondiente
                if(pedido.vehiculo is Moto){
                    Text(text = stringResource(id = (pedido.vehiculo).cilindrada))
                }

                if(pedido.vehiculo is Coche){
                    Text(text = stringResource(id = (pedido.vehiculo).combustible))
                }


                Row {
                    Checkbox(
                        checked = pedido.vehiculo.gps,
                        onCheckedChange = null,
                        Modifier.clickable {}
                    )

                    Text(text = (stringResource(R.string.gps)))
                }

            }
        }
    }
}