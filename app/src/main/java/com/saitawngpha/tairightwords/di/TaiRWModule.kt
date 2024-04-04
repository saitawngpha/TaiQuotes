package com.saitawngpha.tairightwords.di

import android.content.Context
import com.saitawngpha.tairightwords.data.repository.TaiRWRepository
import com.saitawngpha.tairightwords.ui.screens.RightWordViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 28/02/2024.
 */

@Module
@InstallIn(SingletonComponent::class)
object TaiRWModule {

    @Provides
    @Singleton
    fun provideTaiRWRepository(@ApplicationContext context: Context) = TaiRWRepository(context = context)

    @Provides
    @Singleton
    fun provideTaiRWViewModel(taiRWRepository: TaiRWRepository) = RightWordViewModel(taiRWRepository = taiRWRepository)
}