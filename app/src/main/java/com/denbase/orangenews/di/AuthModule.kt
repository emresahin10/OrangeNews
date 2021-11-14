package com.denbase.orangenews.di

import com.denbase.orangenews.repositories.AuthRepository
import com.denbase.orangenews.repositories.MainAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AuthModule {

    @Provides
    @ViewModelScoped
    fun provideRepo() = MainAuthRepository() as AuthRepository
}