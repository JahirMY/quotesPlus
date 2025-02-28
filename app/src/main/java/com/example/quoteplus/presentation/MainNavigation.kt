package com.example.quoteplus.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quoteplus.presentation.ui.screens.AddQuoteScreen
import com.example.quoteplus.presentation.ui.screens.ListQuotesScreen
import com.example.quoteplus.presentation.ui.screens.LoginScreen
import com.example.quoteplus.presentation.viewmodel.ListQuoteViewModel
import com.example.quoteplus.presentation.viewmodel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

data class DrawerMenu(
    val icon: ImageVector,
    val title: String,
    val route: String)

val menus = arrayOf(
    DrawerMenu(Icons.Filled.AccountCircle, "login", MainRoute.Login.name),
    DrawerMenu(Icons.AutoMirrored.Filled.List, "list quotes", MainRoute.GetQuotes.name),
    DrawerMenu(Icons.Filled.Add, "add quote ", MainRoute.AddQuote.name)
)

enum class MainRoute(value: String) {
    Login("login"),
    GetQuotes("getQuotes"),
    AddQuote("addQuote")
}



@Composable
private fun DrawerContent(
    menus: Array<DrawerMenu>,
    onMenuClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                imageVector = Icons.Filled.AccountCircle,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        menus.forEach {
            NavigationDrawerItem(
                label = { Text(text = it.title) },
                icon = { Icon(imageVector = it.icon, contentDescription = null) },
                selected = false,
                onClick = {
                    onMenuClick(it.route)
                }
            )
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    loginViewModel: LoginViewModel,
    listQuoteViewModel: ListQuoteViewModel
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(menus) { route ->
                    coroutineScope.launch {
                        drawerState.close()
                    }
                    navController.navigate(route)
                }
            }
        }
    ) {
        NavHost(navController = navController,
            startDestination = MainRoute.Login.name) {
            composable(MainRoute.Login.name) {
                LoginScreen(drawerState, loginViewModel)
            }
            composable(MainRoute.GetQuotes.name) {
                ListQuotesScreen(drawerState, listQuoteViewModel) {
                    GlobalScope.launch {
                        listQuoteViewModel.getQuotes()
                    }
                }
            }
            composable(MainRoute.AddQuote.name) {
                AddQuoteScreen(drawerState)
            }
        }
    }
}