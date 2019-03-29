package com.android.yamschikovdima.energycalculator.screens.main.di

import androidx.lifecycle.ViewModelProviders
import com.android.yamschikovdima.energycalculator.base.di.PerActivity
import com.android.yamschikovdima.energycalculator.screens.main.presentation.view.MainActivity
import com.android.yamschikovdima.energycalculator.screens.main.presentation.viewmodel.MainViewModel
import com.android.yamschikovdima.energycalculator.screens.main.router.MainRouter

import dagger.Module
import dagger.Provides

@Module
class MainModule(private val activity: MainActivity) {

    @PerActivity
    @Provides
    fun provideVM(): MainViewModel {
        return ViewModelProviders.of(activity).get(MainViewModel::class.java)
    }

    @PerActivity
    @Provides
    fun provideMainActivity(): MainActivity {
        return activity
    }

    @PerActivity
    @Provides
    fun provideRouter(): MainRouter {
        return MainRouter(activity)
    }
}