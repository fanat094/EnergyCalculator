package com.android.yamschikovdima.energycalculator.dagger

import android.content.Context
import com.android.yamschikovdima.energycalculator.base.data.ISharedPreferenceManager
import com.android.yamschikovdima.energycalculator.base.data.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class StorageModule(private val context: Context) {

    @Singleton
    @Provides
    fun providesSharedPreferenceManager(): ISharedPreferenceManager =
        SharedPreferencesManager(context)
}