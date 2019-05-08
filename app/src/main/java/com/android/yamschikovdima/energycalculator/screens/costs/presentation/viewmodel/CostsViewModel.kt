package com.android.yamschikovdima.energycalculator.screens.costs.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.socks.library.KLog

class CostsViewModel():ViewModel(){

    val costsData = MutableLiveData<String>()

    init {
        KLog.e("CostsViewModel","init"+costsData)
    }
}