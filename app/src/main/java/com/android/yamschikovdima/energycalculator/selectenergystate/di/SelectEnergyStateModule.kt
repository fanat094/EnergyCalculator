package com.android.yamschikovdima.energycalculator.selectenergystate.di

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import com.android.yamschikovdima.energycalculator.base.di.PerActivity
import com.android.yamschikovdima.energycalculator.base.di.PerFragment
import com.android.yamschikovdima.energycalculator.screens.main.presentation.view.MainActivity
import com.android.yamschikovdima.energycalculator.screens.main.presentation.viewmodel.MainViewModel
import com.android.yamschikovdima.energycalculator.screens.main.router.MainRouter
import com.android.yamschikovdima.energycalculator.selectenergystate.data.repository.SelectEnergyStateRepositoryImpl
import com.android.yamschikovdima.energycalculator.selectenergystate.domain.SelectEnergyStateInteractor
import com.android.yamschikovdima.energycalculator.selectenergystate.domain.SelectEnergyStateRepository
import com.android.yamschikovdima.energycalculator.selectenergystate.presentation.router.SelectEnergyStateRouter
import com.android.yamschikovdima.energycalculator.selectenergystate.presentation.view.SelectEnergyStateActivity
import com.android.yamschikovdima.energycalculator.selectenergystate.presentation.viewmodel.SelectEnergyStateViewModel

import dagger.Module
import dagger.Provides

@Module
class SelectEnergyStateModule(

    private val activity: SelectEnergyStateActivity
) {

//    @PerActivity
//    @Provides
//    fun provideVM(): SelectEnergyStateViewModel {
//        return ViewModelProviders.of(activity).get(SelectEnergyStateViewModel::class.java)
//    }

    //selectEnergyStateInteractor: SelectEnergyStateInteractor
    @PerActivity
    @Provides
    fun provideVM(selectEnergyStateInteractor: SelectEnergyStateInteractor): SelectEnergyStateViewModel {
        return SelectEnergyStateViewModel(selectEnergyStateInteractor)
    }

    @PerActivity
    @Provides
    fun provideSelectEnergyStateActivity(): SelectEnergyStateActivity {
        return activity
    }

    @PerActivity
    @Provides
    fun provideRouter(): SelectEnergyStateRouter {
        return SelectEnergyStateRouter(activity)
    }

    @PerActivity
    @Provides
    fun provideInteractor(repository: SelectEnergyStateRepository): SelectEnergyStateInteractor {
        return SelectEnergyStateInteractor(repository)
    }

    @PerActivity
    @Provides
    fun provideRepository(context: Context): SelectEnergyStateRepository {
        return SelectEnergyStateRepositoryImpl(context)
    }
}