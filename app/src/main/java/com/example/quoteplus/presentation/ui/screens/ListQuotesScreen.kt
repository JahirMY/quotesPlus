package com.example.quoteplus.presentation.ui.screens

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.quoteplus.domain.model.QuoteUiState
import com.example.quoteplus.presentation.ui.composables.CardText
import com.example.quoteplus.presentation.ui.composables.CircularProgressBar
import com.example.quoteplus.presentation.viewmodel.ListQuoteViewModel
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.example.quoteplus.presentation.ui.composables.QuoteList

@Composable
fun ListQuotesScreen(
    drawerState: DrawerState,
    viewModel: ListQuoteViewModel,
    listQuotesOnClick: () -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val quoteState by viewModel.uiState.collectAsStateWithLifecycle(
        initialValue = QuoteUiState.Loading, lifecycleOwner= lifecycleOwner)

    when (quoteState) {
        is QuoteUiState.Loading -> CircularProgressBar(strokeWidth = 4.dp)
        is QuoteUiState.Data -> QuoteList(quotes = (quoteState as QuoteUiState.Data).list)
        is QuoteUiState.Error -> CardText( fontSize = 16.sp, text = "ERROR:  " +
                (quoteState as QuoteUiState.Error), TextAlign.Center)
        QuoteUiState.Init -> {

        }
    }
}
