package com.example.quoteplus.core.di

import android.content.Context
import com.example.quoteplus.data.local.PreferenceStorageImpl
import com.example.quoteplus.domain.PreferenceStorage
import com.example.quoteplus.presentation.viewmodel.DataStoreViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {
    @Binds
    abstract fun bindPreferenceStorage(
        preferenceStorageImpl: PreferenceStorageImpl
    ): PreferenceStorage

}