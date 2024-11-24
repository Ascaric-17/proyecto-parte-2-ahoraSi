package com.example.pablomolina_proyecto1

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pablomolina_proyecto1.modelo.AppUIState
import com.example.pablomolina_proyecto1.modelo.Pedido

enum class Pantallas(@StringRes val titulo: Int){
    Inicio(titulo = R.string.bienvenido),
    ListaPedidos(titulo = R.string.tus_pedidos),
    FormularioPedido(titulo = R.string.nuevo_pedido),
    ResumenPedido(titulo = R.string.confirma_tu_pedido),
    FormularioPago(titulo = R.string.metodo_de_pago),
    ResumenPago(titulo = R.string.confirma_el_pago)
}

@Composable
fun AlquilerApp(
    viewModel: AppViewModel = viewModel(),
    navController: NavHostController = rememberNavController()) {

    // Para controlar que en la primera pantalla no aparezca la flecha de atrás en el TopBar
    // controlamos si en la pila de retroceso hay alguna patnalla destrás de la actual.
    // Si la hay, mostramos la flecha.
    val pilaRetroceso by navController.currentBackStackEntryAsState()

    val pantallaActual = Pantallas.valueOf(
        pilaRetroceso?.destination?.route ?: Pantallas.Inicio.name
    )

    Scaffold(topBar = {
        AppTopBar(
            pantallaActual = pantallaActual,
            puedeNavegarAtras = navController.previousBackStackEntry != null,
            onNavegarAtras = {navController.navigateUp()}
        )
    }){
        innerPadding ->

        val uiState by viewModel.appUIState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = Pantallas.Inicio.name,
            modifier = Modifier.padding(innerPadding)
        ){

            // Grafo de rutas
            composable(route = Pantallas.Inicio.name){
                Inicio(
                    onBotonListado = {navController.navigate(Pantallas.ListaPedidos.name)},
                    onFABFormulario = {navController.navigate(Pantallas.FormularioPedido.name)},
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            composable(route = Pantallas.ListaPedidos.name){
                ListadoPrincipal(appUIState = uiState)
            }

            composable(route = Pantallas.FormularioPedido.name){
                FormularioMostrar(onBotonResumenPedido = {navController.navigate(Pantallas.ResumenPedido.name)
                                                            viewModel.actualizarPedido(it) })
            }

            composable(route = Pantallas.ResumenPedido.name){
                ResumenPrincipal(onBotonPagar = {navController.navigate(Pantallas.FormularioPago.name) },
                        appUIState = uiState)
            }

            composable(route = Pantallas.FormularioPago.name){
                PagoPrincipal(onAceptarPago = {navController.navigate(Pantallas.ResumenPago.name)
                                              viewModel.actualizarTarjeta(it)})
            }

            composable(route = Pantallas.ResumenPago.name){
                PagoResumenPrincipal(onConfirmarPago = {navController.navigate(Pantallas.Inicio.name)
                                                        viewModel.insertarPedido(it)},
                        appUIState = uiState)
            }

        }
    }




}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    pantallaActual: Pantallas,
    puedeNavegarAtras: Boolean,
    onNavegarAtras: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = { Text(text = stringResource(id = pantallaActual.titulo)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        navigationIcon = {
            if(puedeNavegarAtras) {
                IconButton(onClick = onNavegarAtras) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.atras)
                    )
                }
            }
        },
        modifier = modifier
    )
}