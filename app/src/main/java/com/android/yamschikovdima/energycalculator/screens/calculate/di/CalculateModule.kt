package com.android.yamschikovdima.energycalculator.screens.calculate.di

import android.content.Context
import com.android.yamschikovdima.energycalculator.base.di.PerActivity
import com.android.yamschikovdima.energycalculator.base.viewModelProvide
import com.android.yamschikovdima.energycalculator.screens.calculate.data.repository.CalculateRepositoryImpl
import com.android.yamschikovdima.energycalculator.screens.calculate.domain.CalculateInteractor
import com.android.yamschikovdima.energycalculator.screens.calculate.domain.CalculateRepository
import com.android.yamschikovdima.energycalculator.screens.calculate.presentation.view.CalculateFragment
import com.android.yamschikovdima.energycalculator.screens.calculate.presentation.viewmodel.CalculateViewModel
import com.android.yamschikovdima.energycalculator.screens.costs.presentation.viewmodel.CostsViewModel
import com.android.yamschikovdima.energycalculator.screens.tariffs.data.repository.TariffsRepositoryImpl
import com.android.yamschikovdima.energycalculator.screens.tariffs.domain.TariffsInteractor
import com.android.yamschikovdima.energycalculator.screens.tariffs.domain.TariffsRepository
import com.android.yamschikovdima.energycalculator.screens.tariffs.presentation.viewmodel.TariffsViewModel

import dagger.Module
import dagger.Provides

@Module
class CalculateModule(

    private val fragment: CalculateFragment
) {

    @PerActivity
    @Provides
    fun provideViewModel(calculateInteractor: CalculateInteractor): CalculateViewModel {

        return fragment.viewModelProvide {
            CalculateViewModel(calculateInteractor)
        }

    }

    @PerActivity
    @Provides
    fun provideInteractor(calculateRepository: CalculateRepository): CalculateInteractor {
        return CalculateInteractor(calculateRepository)
    }

    @PerActivity
    @Provides
    fun provideRepository(context: Context): CalculateRepository {
        return CalculateRepositoryImpl(context)
    }
}