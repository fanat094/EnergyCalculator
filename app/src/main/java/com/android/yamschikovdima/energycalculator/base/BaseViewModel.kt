package com.android.yamschikovdima.energycalculator.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel(), LifecycleOwner {

    private val lifecycle: LifecycleRegistry = LifecycleRegistry(this)

    init {
        lifecycle.markState(Lifecycle.State.RESUMED)
    }

    override fun getLifecycle() = lifecycle

    public override fun onCleared() {
        lifecycle.markState(Lifecycle.State.DESTROYED)
    }
}