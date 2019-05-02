package com.android.yamschikovdima.energycalculator.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.toPublisher
import io.reactivex.Flowable

fun <T> LiveData<T>.rx(lifecycle: LifecycleOwner) =
    Flowable.fromPublisher(this.toPublisher(lifecycle))

inline fun <reified T : ViewModel> Fragment.viewModelProvide(crossinline initializer: () -> T): T {
    return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return initializer() as T
        }
    })
        .get(T::class.java)
}