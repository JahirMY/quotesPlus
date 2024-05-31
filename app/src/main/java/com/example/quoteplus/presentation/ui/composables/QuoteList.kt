package com.example.quoteplus.presentation.ui.composables


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.quoteplus.domain.model.QuoteModel

@Composable
fun QuoteList(quotes: List<QuoteModel>){
    LazyColumn {
        items(quotes) {
            QuoteCard(quoteModel = it) {
            }
        }
    }
}


