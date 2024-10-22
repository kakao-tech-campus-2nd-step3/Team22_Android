package com.team22.soundary.feature.main.domain.di

import com.team22.soundary.feature.main.data.ReceivedShareRepositoryImpl
import com.team22.soundary.feature.main.data.SentShareRepositoryImpl
import com.team22.soundary.feature.main.domain.ReceivedShareRepository
import com.team22.soundary.feature.main.domain.SentShareRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ShareRepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindSentShareRepository(impl: SentShareRepositoryImpl) : SentShareRepository

    @Binds
    @ViewModelScoped
    abstract fun bindReceivedShareRepository(impl: ReceivedShareRepositoryImpl) : ReceivedShareRepository
}
