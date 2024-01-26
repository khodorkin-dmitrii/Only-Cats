package com.yavin.mainscreenlib.di

import com.yavin.mainscreenlib.data.UserCatRepository
import com.yavin.mainscreenlib.data.UserCatRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideUserCatRepository(): UserCatRepository {
        return UserCatRepositoryImpl()
    }
}