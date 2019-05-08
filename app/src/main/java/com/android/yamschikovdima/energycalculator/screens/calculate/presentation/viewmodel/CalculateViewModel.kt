package com.android.yamschikovdima.energycalculator.screens.calculate.presentation.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.android.yamschikovdima.energycalculator.base.BaseViewModel
import com.android.yamschikovdima.energycalculator.screens.calculate.domain.CalculateInteractor
import com.socks.library.KLog


class CalculateViewModel(

    private val calculateInteractor: CalculateInteractor

) : BaseViewModel() {

    val isVisible = ObservableBoolean()
    val isVisibleTwo = ObservableBoolean()
    val isVisibleThree = ObservableBoolean()
    val isVisibleResult = ObservableBoolean()

    val normalTariffPreEnergyValue = MutableLiveData<String>()
    val normalTariffCurrentEnergyValue = MutableLiveData<String>()

    //res
    val tariffsResultUsedValue = ObservableField<String>()
    val tariffsResultPreLimitValue = ObservableField<String>()
    val tariffsResultAfterLimitValue = ObservableField<String>()
    val tariffsResultSumValue = ObservableField<String>()

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

//        val val1 = normalTariffPreEnergyValue.value?.toInt()
//        val val2 = normalTariffCurrentEnergyValue.value?.toInt()
//        val res = val2?.minus(val1!!).toString()
//
//        normalTariffUsedEnergyValue.set(res)

        KLog.e("normalTariffEvent", tariffsResultUsedValue)
        calculation()
    }

    fun calculation(){

        val normalTariffPreEnergyValue = normalTariffPreEnergyValue.value?.toInt()
        val normalTariffCurrentEnergyValue = normalTariffCurrentEnergyValue.value?.toInt()
        val differenceValue = normalTariffCurrentEnergyValue?.minus(normalTariffPreEnergyValue!!)

        val afterHundred = differenceValue?.minus(100)

        val afterHundredValue = afterHundred?.times(1.68)

        val preHundredValue = 100*0.9

        val tariffsSumVaue = afterHundredValue?.plus(preHundredValue)

        tariffsResultUsedValue.set(differenceValue.toString())
        tariffsResultPreLimitValue.set(preHundredValue.toString())
        tariffsResultAfterLimitValue.set(afterHundredValue.toString())
        tariffsResultSumValue.set(tariffsSumVaue.toString())

    }

    init {

        KLog.e("CalculateViewModel", "init")

        isVisible.set(true)
//        KLog.e("setChoiceTariffInnnndex", "i_n"+innndex)

        //isVisible.postValue(false)
    }
}