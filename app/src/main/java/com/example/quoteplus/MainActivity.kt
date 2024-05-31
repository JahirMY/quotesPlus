package com.example.quoteplus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quoteplus.data.local.DataStoreManager
import com.example.quoteplus.presentation.MainNavigation
import com.example.quoteplus.presentation.theme.QuotePlusTheme
import com.example.quoteplus.presentation.viewmodel.ListQuoteViewModel
import com.example.quoteplus.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuotePlusTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DataStoreManager.initialize(this)
                    val loginViewModel: LoginViewModel = hiltViewModel()
                    val listQuoteViewModel: ListQuoteViewModel = hiltViewModel()
                    MainNavigation(loginViewModel = loginViewModel,
                        listQuoteViewModel = listQuoteViewModel )
                }
            }
        }
    }
}

