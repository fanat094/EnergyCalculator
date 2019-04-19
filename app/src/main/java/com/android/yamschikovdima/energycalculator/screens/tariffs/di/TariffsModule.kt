package com.android.yamschikovdima.energycalculator.screens.tariffs.di

import android.content.Context
import com.android.yamschikovdima.energycalculator.base.di.PerActivity
import com.android.yamschikovdima.energycalculator.screens.tariffs.data.repository.TariffsRepositoryImpl
import com.android.yamschikovdima.energycalculator.screens.tariffs.domain.TariffsInteractor
import com.android.yamschikovdima.energycalculator.screens.tariffs.domain.TariffsRepository
import com.android.yamschikovdima.energycalculator.screens.tariffs.presentation.view.TariffsFragment
import com.android.yamschikovdima.energycalculator.screens.tariffs.presentation.viewmodel.TariffsViewModel
import com.android.yamschikovdima.energycalculator.selectenergystate.data.repository.SelectEnergyStateRepositoryImpl
import com.android.yamschikovdima.energycalculator.selectenergystate.domain.SelectEnergyStateInteractor
import com.android.yamschikovdima.energycalculator.selectenergystate.domain.SelectEnergyStateRepository
import com.android.yamschikovdima.energycalculator.selectenergystate.presentation.viewmodel.SelectEnergyStateViewModel

import dagger.Module
import dagger.Provides

@Module
class TariffsModule(

    private val fragment: TariffsFragment
) {

    @PerActivity
    @Provides
    fun provideViewModel(tariffsInteractor: TariffsInteractor): TariffsViewModel {
        return TariffsViewModel(tariffsInteractor)
    }

    @PerActivity
    @Provides
    fun provideInteractor(repository: TariffsRepository): TariffsInteractor {
        return TariffsInteractor(repository)
    }

    @PerActivity
    @Provides
    fun provideRepository(context: Context): TariffsRepository {
        return TariffsRepositoryImpl(context)
    }
}