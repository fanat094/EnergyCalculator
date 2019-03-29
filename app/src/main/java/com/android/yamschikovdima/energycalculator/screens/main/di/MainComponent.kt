package com.android.yamschikovdima.energycalculator.screens.main.di

import android.content.Context
import com.android.yamschikovdima.energycalculator.base.di.PerActivity
import com.android.yamschikovdima.energycalculator.dagger.AppComponent
import com.android.yamschikovdima.energycalculator.screens.main.presentation.view.MainActivity
import dagger.Component

@PerActivity
@Component(
    modules = [MainModule::class],
    dependencies = [AppComponent::class]
)
interface MainComponent {
    fun inject(activity: MainActivity)
    fun provideActivity(): MainActivity
    fun provideContext(): Context

}