package com.android.yamschikovdima.energycalculator.dagger

import android.content.Context
import com.android.yamschikovdima.energycalculator.selectenergystate.domain.SelectEnergyStateRepository
import dagger.Component

import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class,
        RepositoryModule::class
    ]
)

@Singleton
interface AppComponent {

    //fun inject(activity: MainActivity)

    fun provideContext(): Context
    //fun provideSelectEnergyStateRepository(): SelectEnergyStateRepository
}