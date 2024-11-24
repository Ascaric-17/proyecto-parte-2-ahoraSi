package com.example.pablomolina_proyecto1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Inicio(onBotonListado: () -> Unit, onFABFormulario: () -> Unit, modifier: Modifier)
{

        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 150.dp, 0.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
            InformacionUsuario()
            BotonListarPedidos(onClick = {onBotonListado()})

        }
        Column(
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 0.dp, 30.dp, 70.dp),

            ) {
            FABNuevoPedido(onClick = {onFABFormulario()})
        }
    }


    @Composable
    fun InformacionUsuario(){
        val iconoUsuario = painterResource(id = R.drawable.usericon)
        Column (modifier = Modifier
            .padding(0.dp, 150.dp, 0.dp, 0.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
            Icon(painter = iconoUsuario,
                contentDescription = null,
                Modifier.size(80.dp),
            )
            Text(text = stringResource(R.string.lorena_ipsum_dolores), fontSize = 18.sp)
            Text(text = stringResource(R.string.loremipsum_dolorsitam_es), fontSize = 18.sp)
            Text(text = stringResource(R.string._612_345_678), fontSize = 18.sp)
        }
    }

    @Composable
    fun BotonListarPedidos(onClick: () -> Unit){
        Button(onClick = onClick, modifier = Modifier.padding(50.dp)) {
            Text(text = stringResource(R.string.listar_pedidos))
        }
    }

    @Composable
    fun FABNuevoPedido(onClick: () -> Unit){
        ExtendedFloatingActionButton(onClick = onClick,
            icon = { Icon(Icons.Filled.Add, "Add FAB") },
            text = { Text(text = stringResource(R.string.nuevo_pedido)) }
        )
    }
