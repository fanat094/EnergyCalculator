package com.android.yamschikovdima.energycalculator.screens.calculate.presentation.viewmodel

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.android.yamschikovdima.energycalculator.base.BaseViewModel
import com.android.yamschikovdima.energycalculator.base.SingleLiveEvent
import com.android.yamschikovdima.energycalculator.screens.calculate.domain.CalculateInteractor
import com.android.yamschikovdima.energycalculator.utils.CalculatingUtil
import com.android.yamschikovdima.energycalculator.utils.FormValidator
import com.android.yamschikovdima.energycalculator.utils.FormattingUtil
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

    //normal Tariff
    val normalTariffPreEnergyValue = MutableLiveData<String>()
    val normalTariffCurrentEnergyValue = MutableLiveData<String>()

    //twoZone Tariff
    val twoZoneTariffDayPreEnergyValue = MutableLiveData<String>()
    val twoZoneTariffNightPreEnergyValue = MutableLiveData<String>()
    val twoZoneTariffDayCurrentEnergyValue = MutableLiveData<String>()
    val twoZoneTariffNightCurrentEnergyValue = MutableLiveData<String>()

    val twoZoneTariffDayPreValue = null

    val checkTariffCurrentEnergyValue = SingleLiveEvent<Boolean>()

    //res
    val tariffsResultUsedValue = ObservableField<String>()
    val tariffsResultPreLimitValue = ObservableField<String>()
    val tariffsResultAfterLimitValue = ObservableField<String>()
    val tariffsResultSumValue = ObservableField<String>()

    //coroutine
    private val uiDispatcher: CoroutineDispatcher = Dispatchers.Main
    private val job = Job()

    val mNormalTariffDayValueFormValidator = FormValidator(uiDispatcher + job)
    val mNormalTariffNightValueFormValidator = FormValidator(uiDispatcher + job)

    //utils
    private val formattingUtil = FormattingUtil()
    private val calculatingUtil = CalculatingUtil()

    private val selectedRegionEraMax = MutableLiveData<String>()
    private val selectedRegionEraMini = MutableLiveData<String>()

    //2tariff
    private val selectedRegionDayMini = MutableLiveData<String>()
    private val selectedRegionNightMini = MutableLiveData<String>()

    fun setChoiceTariff(index: Int) {
        KLog.e("setChoiceTariff", index)

        if (index == NORMALTARIFFINDEX) {
            isVisibleResult.set(false)
            isVisibleTwo.set(false)
            isVisibleThree.set(false)
            isVisible.set(true)
        }
        if (index == TWOZONETARIFFINDEX) {
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
            calculationNormalTariff()
        } else {
            checkTariffCurrentEnergyValue.value = false
            isVisibleResult.set(false)
        }
    }

    private fun calculationNormalTariff() {

        val normalTariffPreEnergyValue = normalTariffPreEnergyValue.value?.toInt()
        val normalTariffCurrentEnergyValue = normalTariffCurrentEnergyValue.value?.toInt()

        //calculation
        val differenceValue = calculatingUtil.calculateMinusOperator(normalTariffPreEnergyValue, normalTariffCurrentEnergyValue)
        //val differenceValue = normalTariffPreEnergyValue?.let { normalTariffCurrentEnergyValue?.minus(it) }

        val afterHundred = calculatingUtil.calculateMinusHundredOperator(differenceValue)
        //val afterHundred = differenceValue?.minus(ONEHUNDRED)

        val afterHundredValue = calculatingUtil.calculateMinusAfterHundredOperator(selectedRegionEraMax.value, afterHundred)
        //val afterHundredValue = selectedRegionEraMax.value?.toDouble()?.let { afterHundred?.times(it) }

        val preHundredValue = calculatingUtil.calculateMinusPreHundredOperator(selectedRegionEraMini.value)
        //val preHundredValue = selectedRegionEraMini.value?.toDouble()?.let { ONEHUNDRED.times(it) }

        val tariffsSumValue = calculatingUtil.calculateTariffsSumValueOperator(preHundredValue, afterHundredValue)
        //val tariffsSumValue = preHundredValue?.let { afterHundredValue?.plus(it) }

        //set calculation
        tariffsResultUsedValue.set(formattingUtil.formatKilowattShort(differenceValue))
        tariffsResultPreLimitValue.set(formattingUtil.formatUaHryvnyaShort(preHundredValue))
        tariffsResultAfterLimitValue.set(formattingUtil.formatUaHryvnyaShort(afterHundredValue))
        tariffsResultSumValue.set(formattingUtil.formatUaHryvnyaShort(tariffsSumValue))
    }

    fun twoZoneTariffEvent() {

        val twoZoneTariffDayPreValue = twoZoneTariffDayPreEnergyValue.value?.toInt()
        val twoZoneTariffNightPreValue = twoZoneTariffNightPreEnergyValue.value?.toInt()
        val twoZoneTariffDayCurrentValue = twoZoneTariffDayCurrentEnergyValue.value?.toInt()
        val twoZoneTariffNightCurrentValue = twoZoneTariffNightCurrentEnergyValue.value?.toInt()

        if (twoZoneTariffDayCurrentValue != null) {
            if (twoZoneTariffNightCurrentValue != null) {
                if (twoZoneTariffDayCurrentValue > twoZoneTariffDayPreValue!! &&
                    twoZoneTariffNightCurrentValue > twoZoneTariffNightPreValue!!) {

                    checkTariffCurrentEnergyValue.value = true
                    KLog.e("checkTariffCurrentEnergyValue", checkTariffCurrentEnergyValue.value)
                    isVisibleResult.set(true)
                    calculationTwoZoneTariff()
                } else {
                    checkTariffCurrentEnergyValue.value = false
                    isVisibleResult.set(false)
                }
            }
        }
    }

    private fun calculationTwoZoneTariff() {

        val twoZoneTariffDayPreValue = twoZoneTariffDayPreEnergyValue.value?.toInt()
        val twoZoneTariffNightPreValue = twoZoneTariffNightPreEnergyValue.value?.toInt()
        val twoZoneTariffDayCurrentValue = twoZoneTariffDayCurrentEnergyValue.value?.toInt()
        val twoZoneTariffNightCurrentValue = twoZoneTariffNightCurrentEnergyValue.value?.toInt()

        //calculation
        val differenceDayValue = calculatingUtil.calculateMinusOperator(twoZoneTariffDayPreValue, twoZoneTariffDayCurrentValue)
        val differenceNightValue = calculatingUtil.calculateMinusOperator(twoZoneTariffNightPreValue, twoZoneTariffNightCurrentValue)

        val tariffsSumValue = calculatingUtil.calculateTariffsSumValueOperator(differenceDayValue, differenceNightValue)

        val tariffsPercentValueDay = calculatingUtil.calculateTariffsPercentValueOperator(differenceDayValue,tariffsSumValue)
        val tariffsPercentValueNight = calculatingUtil.calculateTariffsPercentValueOperator(differenceNightValue,tariffsSumValue)

        val tariffsDaySpendEnergyPreHundred = calculatingUtil.calculateDaySpendEnergyPreHundredOperator(selectedRegionDayMini.value, tariffsPercentValueDay)
        val tariffsNightSpendEnergyPreHundred = calculatingUtil.calculateDaySpendEnergyPreHundredOperator(selectedRegionNightMini.value, tariffsPercentValueNight)

        KLog.e("2Tariff",
            tariffsSumValue,
            tariffsPercentValueDay,
            tariffsPercentValueNight,
            tariffsDaySpendEnergyPreHundred,
            tariffsNightSpendEnergyPreHundred)



    }

    init {

        KLog.e("CalculateViewModel", "init")
        isVisible.set(true)
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun setSelelectedRegionId(selelectedRegionId: Int) {
        Log.e("setSelectedRegionId", " $selelectedRegionId")

        val energyState = calculateInteractor.getSelectedEnergyState(selelectedRegionId)
        Log.e("setSelectedEnergyState", " " + energyState.calculator[NORMALTARIFFINDEX].era_max)

        selectedRegionEraMini.value = energyState.calculator[NORMALTARIFFINDEX].era_mini
        selectedRegionEraMax.value = energyState.calculator[NORMALTARIFFINDEX].era_max

        //2tariff
        selectedRegionDayMini.value = energyState.calculator[TWOZONETARIFFINDEX].day_mini
        selectedRegionNightMini.value = energyState.calculator[TWOZONETARIFFINDEX].night_mini

    }

    companion object {

        private const val NORMALTARIFFINDEX: Int = 0
        private const val TWOZONETARIFFINDEX: Int = 1
    }
}