package com.android.yamschikovdima.energycalculator.screens.tariffs.di

import android.content.Context
import com.android.yamschikovdima.energycalculator.base.di.PerFragment
import com.android.yamschikovdima.energycalculator.base.viewModelProvide
import com.android.yamschikovdima.energycalculator.screens.tariffs.data.repository.TariffsRepositoryImpl
import com.android.yamschikovdima.energycalculator.screens.tariffs.domain.TariffsInteractor
import com.android.yamschikovdima.energycalculator.screens.tariffs.domain.TariffsRepository
import com.android.yamschikovdima.energycalculator.screens.tariffs.presentation.view.TariffsFragment
import com.android.yamschikovdima.energycalculator.screens.tariffs.presentation.viewmodel.TariffsViewModel
import dagger.Module
import dagger.Provides

@Module
class TariffsModule(

    private val fragment: TariffsFragment
) {

    @PerFragment
    @Provides
    fun provideViewModel(tariffsInteractor: TariffsInteractor): TariffsViewModel {
        return fragment.viewModelProvide { TariffsViewModel(tariffsInteractor) }
    }

    @PerFragment
    @Provides
    fun provideInteractor(repository: TariffsRepository): TariffsInteractor {
        return TariffsInteractor(repository)
    }

    @PerFragment
    @Provides
    fun provideRepository(context: Context): TariffsRepository {
        return TariffsRepositoryImpl(context)
    }
}