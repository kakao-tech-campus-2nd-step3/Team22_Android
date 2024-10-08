package com.team22.soundary.feature.main.domain

import com.team22.soundary.feature.main.data.ShareRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class ShareRepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindShareRepository(impl: ShareRepositoryImpl): ShareRepository
}
