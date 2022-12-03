package com.pavellukyanov.themartiancompose.di

import android.content.Context
import androidx.room.Room
import com.pavellukyanov.themartiancompose.data.cache.MartianDatabase
import com.pavellukyanov.themartiancompose.data.cache.dao.RoverDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalCacheModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MartianDatabase {
        return Room.databaseBuilder(
            context,
            MartianDatabase::class.java,
            "MartianDatabase.db"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRoverDao(db: MartianDatabase): RoverDao = db.rovers()
}