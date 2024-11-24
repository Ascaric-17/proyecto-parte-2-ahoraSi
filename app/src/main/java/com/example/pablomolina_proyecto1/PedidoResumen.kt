package com.example.pablomolina_proyecto1


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pablomolina_proyecto1.modelo.AppUIState
import com.example.pablomolina_proyecto1.modelo.Coche
import com.example.pablomolina_proyecto1.modelo.Moto
import com.example.pablomolina_proyecto1.modelo.Tarjeta

@Composable
fun ResumenPrincipal(modifier: Modifier = Modifier, onBotonPagar: () -> Unit, appUIState: AppUIState) {
    val pedido = appUIState.pedido
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 120.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ){
        Text(text = stringResource(R.string.tu_pedido))

        Card(
            modifier
                .fillMaxWidth()
                .padding(20.dp)) {
            Row (verticalAlignment = Alignment.CenterVertically){
                if(pedido!!.vehiculo is Moto){
                    Image(

                        painter = painterResource(id = R.drawable.moto),
                        contentDescription = stringResource(id = R.string.moto),
                        modifier = Modifier
                            .padding(10.dp)
                            .size(200.dp)
                    )
                } else if(pedido!!.vehiculo is Coche) {
                    Image(

                        painter = painterResource(id = R.drawable.coche),
                        contentDescription = stringResource(id = R.string.coche),
                        modifier = Modifier
                            .padding(10.dp)
                            .size(200.dp)
                    )
                } else{
                    Image(

                        painter = painterResource(id = R.drawable.patin),
                        contentDescription = stringResource(id = R.string.patin),
                        modifier = Modifier
                            .padding(10.dp)
                            .size(200.dp)
                    )
                }
                Column{
                    if(appUIState.pedido!!.vehiculo is Moto){
                        Text(
                          text = stringResource(id = R.string.moto)
                        )
                        Text(
                            text = stringResource((pedido!!.vehiculo as Moto).cilindrada)
                        )
                    } else if(pedido!!.vehiculo is Coche) {
                        Text(
                            text = stringResource(id = R.string.coche)
                        )
                        Text(
                            text = stringResource((pedido!!.vehiculo as Coche).combustible)
                        )
                    } else {
                        Text(
                            text = stringResource(id = R.string.patin)
                        )
                    }
                    if(pedido!!.vehiculo!!.gps == true){
                        Text(
                            text = stringResource(id = R.string.gps)
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .padding(16.dp)
                .padding(bottom = 100.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center){

            Button(onClick = {onBotonPagar()}, modifier = Modifier.padding(20.dp)) {
                Text(text = stringResource(R.string.pagar))
            }
        }
    }
}

