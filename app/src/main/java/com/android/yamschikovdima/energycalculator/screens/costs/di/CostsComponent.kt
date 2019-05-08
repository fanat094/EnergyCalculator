package com.android.yamschikovdima.energycalculator.screens.costs.di

import android.content.Context
import com.android.yamschikovdima.energycalculator.base.di.PerFragment
import com.android.yamschikovdima.energycalculator.dagger.AppComponent
import com.android.yamschikovdima.energycalculator.screens.costs.presentation.view.CostsFragment
import dagger.Component

@PerFragment
@Component(
    modules = [CostsModule::class],
    dependencies = [AppComponent::class]
)
interface CostsComponent {
    fun inject(fragment: CostsFragment)
    fun provideContext(): Context
}