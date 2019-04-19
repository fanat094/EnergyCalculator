package com.android.yamschikovdima.energycalculator

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.android.yamschikovdima.energycalculator.base.di.ComponentHolder
import com.android.yamschikovdima.energycalculator.dagger.AppComponent
import com.android.yamschikovdima.energycalculator.dagger.AppModule
import com.android.yamschikovdima.energycalculator.dagger.DaggerAppComponent
import com.android.yamschikovdima.energycalculator.dagger.StorageModule


class EnergyCalculatorApplication : Application(), ComponentHolder<AppComponent> {

    override val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .storageModule(StorageModule(this))
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = component

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}