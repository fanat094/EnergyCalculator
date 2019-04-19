package com.android.yamschikovdima.energycalculator.screens.tariffs.di

import android.content.Context
import com.android.yamschikovdima.energycalculator.base.di.PerActivity
import com.android.yamschikovdima.energycalculator.dagger.AppComponent
import com.android.yamschikovdima.energycalculator.screens.tariffs.presentation.view.TariffsFragment
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