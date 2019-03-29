package com.android.yamschikovdima.energycalculator.dagger

import android.content.Context
import dagger.Component

import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class
    ]
)

@Singleton
interface AppComponent {

    //fun inject(activity: MainActivity)

    fun provideContext(): Context
}