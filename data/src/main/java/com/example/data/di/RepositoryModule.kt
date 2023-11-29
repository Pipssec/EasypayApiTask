package com.example.data.di

import com.example.data.repository.ContentRepository
import com.example.data.repository.ContentRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideContentRepo(impl : ContentRepositoryImpl) : ContentRepository

}