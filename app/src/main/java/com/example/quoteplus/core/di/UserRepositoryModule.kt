package com.example.quoteplus.core.di

import com.example.quoteplus.data.UserRepositoryImpl
import com.example.quoteplus.data.remote.UserRemoteDataSource
import com.example.quoteplus.data.remote.UserRemoteDataSourceImpl
import com.example.quoteplus.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent



@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl):
            UserRepository

    @Binds
    abstract fun bindUserRemoteDataSource(userRemoteDataSourceImpl: UserRemoteDataSourceImpl):
            UserRemoteDataSource

}