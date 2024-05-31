package com.example.quoteplus.core.di

import com.example.quoteplus.data.remote.QuoteRemoteDataSource
import com.example.quoteplus.data.remote.QuoteRemoteDataSourceImpl
import com.example.quoteplus.data.remote.QuoteRepositoryImpl
import com.example.quoteplus.domain.QuoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class QuoteRepositoryModule {

    @Binds
    abstract fun bindQuoteRepository(quoteRepositoryImpl: QuoteRepositoryImpl):
            QuoteRepository

    @Binds
    abstract fun bindQuoteRemoteDataSource(quoteRemoteDataSourceImpl: QuoteRemoteDataSourceImpl):
            QuoteRemoteDataSource

}