package com.yavin.mainscreenlib.di

import com.yavin.mainscreenlib.data.UserCatsRepository
import com.yavin.mainscreenlib.data.UserCatsRepositoryImpl
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
    fun provideUserCatRepository(): UserCatsRepository {
        return UserCatsRepositoryImpl()
    }
}