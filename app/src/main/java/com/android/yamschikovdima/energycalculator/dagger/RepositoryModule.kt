package com.android.yamschikovdima.energycalculator.dagger

import android.content.Context
import com.android.yamschikovdima.energycalculator.base.di.PerFragment
import com.android.yamschikovdima.energycalculator.screens.tariffs.data.repository.TariffsRepositoryImpl
import com.android.yamschikovdima.energycalculator.screens.tariffs.domain.TariffsRepository
import com.android.yamschikovdima.energycalculator.selectenergystate.data.repository.SelectEnergyStateRepositoryImpl
import com.android.yamschikovdima.energycalculator.selectenergystate.domain.SelectEnergyStateRepository
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class RepositoryModule {

//    @Singleton
//    @Provides
//    fun provideTariffsRepository(context: Context): TariffsRepository {
//        return TariffsRepositoryImpl(context)
//    }

}