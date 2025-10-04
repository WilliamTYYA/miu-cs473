package com.miu.lesson5_part2.di

import android.util.Log
import com.miu.lesson5_part2.data.AlphabetRepository
import com.miu.lesson5_part2.data.AlphabetRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
//    @Singleton
    fun provideAlphabetRepository(): AlphabetRepository {
        Log.d("RepsitoryModule", "prvideAlphabetRepository")
        return AlphabetRepositoryImp()
    }
}