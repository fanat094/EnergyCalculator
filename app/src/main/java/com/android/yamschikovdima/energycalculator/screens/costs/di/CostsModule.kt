package com.android.yamschikovdima.energycalculator.screens.costs.di

import android.content.Context
import com.android.yamschikovdima.energycalculator.base.di.PerActivity
import com.android.yamschikovdima.energycalculator.base.di.PerFragment
import com.android.yamschikovdima.energycalculator.base.viewModelProvide
import com.android.yamschikovdima.energycalculator.screens.costs.presentation.view.CostsFragment
import com.android.yamschikovdima.energycalculator.screens.costs.presentation.viewmodel.CostsViewModel


import dagger.Module
import dagger.Provides

@Module
class CostsModule(

    private val fragment: CostsFragment
) {

    @PerActivity
    @Provides
    fun provideViewModel(): CostsViewModel {
        return fragment.viewModelProvide {
            CostsViewModel()
        }
    }

}