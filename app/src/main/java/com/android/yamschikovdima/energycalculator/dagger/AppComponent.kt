package com.android.yamschikovdima.energycalculator.dagger

import android.content.Context
import com.android.yamschikovdima.energycalculator.base.data.ISharedPreferenceManager
import com.android.yamschikovdima.energycalculator.screens.tariffs.domain.TariffsRepository
import com.android.yamschikovdima.energycalculator.selectenergystate.domain.SelectEnergyStateRepository
import dagger.Component

import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        RepositoryModule::class,
        StorageModule::class
    ]
)

@Singleton
interface AppComponent {

    //fun inject(activity: MainActivity)

    fun provideContext(): Context
    fun provideISharedPreferenceManager(): ISharedPreferenceManager
    //fun provideTariffsRepository():TariffsRepository
}