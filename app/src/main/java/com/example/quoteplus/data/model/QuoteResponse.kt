package com.example.quoteplus.data.model

import com.example.quoteplus.domain.model.QuoteModel

class QuoteResponse (val success:Boolean,
                     val message: String,
                     val data: List<QuoteModel>)