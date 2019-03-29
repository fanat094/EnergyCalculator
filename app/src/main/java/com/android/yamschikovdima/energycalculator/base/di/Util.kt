package com.android.yamschikovdima.energycalculator.base.di

import android.content.Context
import com.android.yamschikovdima.energycalculator.dagger.AppComponent

interface ComponentHolder<T> {
    val component: T
}

@Suppress("UNCHECKED_CAST")
fun Context.appComponent() =
        (this.applicationContext as? ComponentHolder<AppComponent>)
                ?.component ?: error("Application must implements ComponentHolder<AppComponent>")

@Suppress("UNCHECKED_CAST")
fun androidx.fragment.app.Fragment.appComponent() =
        context?.appComponent()
                ?: error("Must call after attach to context")

inline fun <reified T> androidx.fragment.app.Fragment.activityComponent(): T {
    @Suppress("UNCHECKED_CAST")
    return (activity as ComponentHolder<T>).component
}