package com.android.yamschikovdima.energycalculator.screens.costs.di

import android.content.Context
import com.android.yamschikovdima.energycalculator.base.di.PerActivity
import com.android.yamschikovdima.energycalculator.base.di.PerFragment
import com.android.yamschikovdima.energycalculator.dagger.AppComponent
import com.android.yamschikovdima.energycalculator.screens.costs.presentation.view.CostsFragment
import com.android.yamschikovdima.energycalculator.screens.tariffs.presentation.view.TariffsFragment
import com.android.yamschikovdima.energycalculator.screens.tariffs.presentation.viewmodel.TariffsViewModel
import com.android.yamschikovdima.energycalculator.selectenergystate.data.repository.SelectEnergyStateRepositoryImpl
import dagger.Component

@PerActivity
@Component(
    modules = [CostsModule::class],
    dependencies = [AppComponent::class]
)
interface CostsComponent {
    fun inject(fragment: CostsFragment)
    fun provideContext(): Context
}