package com.android.yamschikovdima.energycalculator.screens.calculate.presentation.viewmodel

import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.android.yamschikovdima.energycalculator.base.BaseViewModel
import com.android.yamschikovdima.energycalculator.screens.calculate.domain.CalculateInteractor
import com.android.yamschikovdima.energycalculator.screens.tariffs.domain.TariffsInteractor
import com.socks.library.KLog
import androidx.databinding.ObservableField
import com.socks.library.KLog.init


class CalculateViewModel(

    private val calculateInteractor: CalculateInteractor

) : BaseViewModel() {

    val isVisible = ObservableBoolean()
    val isVisibleTwo = ObservableBoolean()
    val isVisibleThree = ObservableBoolean()
    val isVisibleResult = ObservableBoolean()

    val normalTariffPreEnergyValue = MutableLiveData<String>()
    val normalTariffCurrentEnergyValue = MutableLiveData<String>()
    val normalTariffUsedEnergyValue = ObservableField<String>()

    var innndex = ""

    fun setChoiceTariff(index: Int) {
        KLog.e("setChoiceTariff", index)

        if (index == 0) {
            isVisibleResult.set(false)
            isVisibleTwo.set(false)
            isVisibleThree.set(false)
            isVisible.set(true)
        }
        if (index == 1) {
            isVisibleResult.set(false)
            isVisible.set(false)
            isVisibleThree.set(false)
            isVisibleTwo.set(true)
        }
        if (index == 2) {
            isVisibleResult.set(false)
            isVisible.set(false)
            isVisibleTwo.set(false)
            isVisibleThree.set(true)
        }
    }

    fun normalTariffEvent() {

        isVisibleResult.set(true)

        val val1 = normalTariffPreEnergyValue.value?.toInt()
        val val2 = normalTariffCurrentEnergyValue.value?.toInt()
        val res = val2?.minus(val1!!).toString()

        normalTariffUsedEnergyValue.set(res)

        KLog.e("normalTariffEvent", normalTariffUsedEnergyValue)
    }

    init {
        isVisible.set(true)
//        KLog.e("setChoiceTariffInnnndex", "i_n"+innndex)

        //isVisible.postValue(false)
    }
}