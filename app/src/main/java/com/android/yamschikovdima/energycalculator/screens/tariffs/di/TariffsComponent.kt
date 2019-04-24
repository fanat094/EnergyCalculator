package com.android.yamschikovdima.energycalculator.screens.tariffs.di

import android.content.Context
import com.android.yamschikovdima.energycalculator.base.di.PerActivity
import com.android.yamschikovdima.energycalculator.dagger.AppComponent
import com.android.yamschikovdima.energycalculator.screens.tariffs.presentation.view.TariffsFragment
import com.android.yamschikovdima.energycalculator.screens.tariffs.presentation.viewmodel.TariffsViewModel
import com.android.yamschikovdima.energycalculator.selectenergystate.data.repository.SelectEnergyStateRepositoryImpl
import dagger.Component

@PerActivity
@Component(
    modules = [TariffsModule::class],
    dependencies = [AppComponent::class]
)
interface TariffsComponent {
    fun inject(fragment: TariffsFragment)
    fun provideContext(): Context
}