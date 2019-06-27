package com.android.yamschikovdima.energycalculator.screens.calculate.presentation.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.android.yamschikovdima.energycalculator.base.BaseViewModel
import com.android.yamschikovdima.energycalculator.screens.calculate.domain.CalculateInteractor
import com.android.yamschikovdima.energycalculator.utils.LoginFormValidator
import com.socks.library.KLog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job


class CalculateViewModel(

    private val calculateInteractor: CalculateInteractor

) : BaseViewModel() {

    val isVisible = ObservableBoolean()
    val isVisibleTwo = ObservableBoolean()
    val isVisibleThree = ObservableBoolean()
    val isVisibleResult = ObservableBoolean()

    val normalTariffPreEnergyValue = MutableLiveData<String>()
    val normalTariffCurrentEnergyValue = MutableLiveData<String>()

    val checkTariffCurrentEnergyValue = MutableLiveData<Boolean>()

    //res
    val tariffsResultUsedValue = ObservableField<String>()
    val tariffsResultPreLimitValue = ObservableField<String>()
    val tariffsResultAfterLimitValue = ObservableField<String>()
    val tariffsResultSumValue = ObservableField<String>()

    var innndex = ""

    private val uiDispatcher: CoroutineDispatcher = Dispatchers.Main
    private val job = Job()

    val mLoginFormValidator = LoginFormValidator(uiDispatcher + job)
    val mLoginFormValidator2 = LoginFormValidator(uiDispatcher + job)

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

        val normalTariffPreEnergyValue = normalTariffPreEnergyValue.value?.toInt()
        val normalTariffCurrentEnergyValue = normalTariffCurrentEnergyValue.value?.toInt()

        if (normalTariffCurrentEnergyValue!! > normalTariffPreEnergyValue!!) {

            checkTariffCurrentEnergyValue.value = true
            KLog.e("checkTariffCurrentEnergyValue", checkTariffCurrentEnergyValue.value)
            isVisibleResult.set(true)
            calculation()
        }
        else{
            checkTariffCurrentEnergyValue.value = false
            isVisibleResult.set(false)
        }
    }

    fun calculation() {

        val normalTariffPreEnergyValue = normalTariffPreEnergyValue.value?.toInt()
        val normalTariffCurrentEnergyValue = normalTariffCurrentEnergyValue.value?.toInt()

        //cal
        val differenceValue = normalTariffCurrentEnergyValue?.minus(normalTariffPreEnergyValue!!)

        val afterHundred = differenceValue?.minus(100)

        val afterHundredValue = afterHundred?.times(1.68)

        val preHundredValue = 100 * 0.9

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

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}