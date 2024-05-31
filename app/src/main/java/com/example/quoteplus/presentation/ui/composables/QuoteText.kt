package com.example.quoteplus.presentation.ui.composables


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

@Composable
fun QuoteText(fontSize: TextUnit, text: String, align: TextAlign){
    Text(
        text = text,
        style = TextStyle(fontSize = fontSize),
        textAlign = align,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}