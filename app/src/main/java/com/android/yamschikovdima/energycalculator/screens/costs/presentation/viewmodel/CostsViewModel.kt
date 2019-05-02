package com.android.yamschikovdima.energycalculator.screens.costs.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.android.yamschikovdima.energycalculator.base.BaseViewModel
import com.socks.library.KLog

class CostsViewModel:BaseViewModel(){

    val costsData = MutableLiveData<String>()

    init {
        KLog.e("CostsViewModel","init")
    }

}