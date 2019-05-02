package com.android.yamschikovdima.energycalculator.screens.main.presentation.viewmodel

import android.view.MenuItem
import androidx.lifecycle.MutableLiveData
import com.android.yamschikovdima.energycalculator.R
import com.android.yamschikovdima.energycalculator.base.BaseViewModel
import com.android.yamschikovdima.energycalculator.screens.main.router.MainBottomTab
import com.socks.library.KLog

class MainViewModel : BaseViewModel() {

    val selected = MutableLiveData<MainBottomTab>()

    fun onNavigationClick(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.calculate_section -> selected.postValue(MainBottomTab.CALCULATE)
            R.id.costs_section -> selected.postValue(MainBottomTab.COSTS)
            R.id.tariffs_section -> selected.postValue(MainBottomTab.TARIFFS)
            else -> error("Unsupported menu item")
        }
        return true
    }

    init {
        KLog.e("MainViewModel", "init")
    }
}