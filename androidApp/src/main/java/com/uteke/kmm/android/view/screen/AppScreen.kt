package com.uteke.kmm.android.view.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.compose.get

@ExperimentalCoroutinesApi
@ExperimentalMaterial3Api
@Composable
fun AppScreen() {
    val navController = rememberNavController()
    MaterialTheme {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "Products",
                                fontSize = 20.sp,
                                modifier = Modifier.align(Alignment.Center),
                                color = MaterialTheme.colorScheme.surface
                            )
                        }
                    },
                    navigationIcon = {
                        Box(contentAlignment = Alignment.Center) {
                            IconButton(
                                onClick = { navController.popBackStack() },
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.surface
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                )
            },
            content = { paddingValues ->
                NavGraph(
                    navController = navController,
                    paddingValues = paddingValues,
                )
            }
        )
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
private fun NavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) =
    NavHost(navController = navController, startDestination = "products") {
        composable(route = "products") {
            ProductListScreen(
                modifier = Modifier.padding(paddingValues),
                viewModel = get(),
                navController = navController
            )
        }
        composable(
            route = "products/{productId}",
            arguments = listOf(
                navArgument(
                    name = "productId",
                    builder = {
                        type = NavType.IntType
                        nullable = false
                        defaultValue = 0
                    }
                )
            )
        ) { backStackEntry ->
            ProductItemScreen(
                modifier = Modifier.padding(paddingValues),
                viewModel = get(),
                productId = backStackEntry.arguments?.getInt("productId", 0) ?: 0
            )
        }
    }