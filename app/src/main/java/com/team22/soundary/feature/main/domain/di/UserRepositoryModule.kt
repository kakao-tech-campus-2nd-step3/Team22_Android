package com.team22.soundary.feature.main.domain.di

import com.team22.soundary.feature.main.data.UserRepositoryImpl
import com.team22.soundary.feature.main.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserRepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindUserRepository(impl: UserRepositoryImpl) : UserRepository
}