package com.android.yamschikovdima.energycalculator.selectenergystate.di

import android.content.Context
import com.android.yamschikovdima.energycalculator.base.di.PerActivity
import com.android.yamschikovdima.energycalculator.dagger.AppComponent
import com.android.yamschikovdima.energycalculator.screens.main.di.MainModule
import com.android.yamschikovdima.energycalculator.screens.main.presentation.view.MainActivity
import com.android.yamschikovdima.energycalculator.selectenergystate.data.repository.SelectEnergyStateRepositoryImpl
import com.android.yamschikovdima.energycalculator.selectenergystate.presentation.view.SelectEnergyStateActivity
import dagger.Component

@PerActivity
@Component(
    modules = [SelectEnergyStateModule::class],
    dependencies = [AppComponent::class]
)
interface SelectEnergyStateComponent {
    fun inject(activity: SelectEnergyStateActivity)
    fun provideContext(): Context
}