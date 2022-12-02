package com.pavellukyanov.themartiancompose.di

import com.pavellukyanov.themartiancompose.data.repositories.RoverRepositoryImpl
import com.pavellukyanov.themartiancompose.domain.usecases.home.RoverRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindRoverRepository(impl: RoverRepositoryImpl): RoverRepository
}