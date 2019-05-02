package com.android.yamschikovdima.energycalculator.screens.calculate.di

import android.content.Context
import com.android.yamschikovdima.energycalculator.base.di.PerActivity
import com.android.yamschikovdima.energycalculator.base.di.PerFragment
import com.android.yamschikovdima.energycalculator.dagger.AppComponent
import com.android.yamschikovdima.energycalculator.screens.calculate.presentation.view.CalculateFragment

import dagger.Component

@PerActivity
@Component(
    modules = [CalculateModule::class],
    dependencies = [AppComponent::class]
)
interface CalculateComponent {
    fun inject(fragment: CalculateFragment)
    fun provideContext(): Context
}