package com.android.yamschikovdima.energycalculator.screens.tariffs.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.android.yamschikovdima.energycalculator.base.BaseViewModel
import com.android.yamschikovdima.energycalculator.screens.tariffs.domain.TariffsInteractor
import com.android.yamschikovdima.energycalculator.selectenergystate.data.model.EnergyState
import com.socks.library.KLog

class TariffsViewModel(

    private val tariffsInteractor: TariffsInteractor

) : BaseViewModel() {

    private val selelectedRegionIdValue = MutableLiveData<Int>()
    val normalTariffDayValue = MutableLiveData<String>()
    val normalTariffNightValue = MutableLiveData<String>()
    val normalTariffDayMaxValue = MutableLiveData<String>()
    val normalTariffNightMaxValue = MutableLiveData<String>()

    val twoZoneTariffDayValue = MutableLiveData<String>()
    val twoZoneTariffNightValue = MutableLiveData<String>()
    val twoZoneTariffDayMaxValue = MutableLiveData<String>()
    val twoZoneTariffNightMaxValue = MutableLiveData<String>()

    val threeZoneTariffDayValue = MutableLiveData<String>()
    val threeZoneTariffNightValue = MutableLiveData<String>()
    val threeZoneTariffPeakValue = MutableLiveData<String>()

    val threeZoneTariffDayMaxValue = MutableLiveData<String>()
    val threeZoneTariffNightMaxValue = MutableLiveData<String>()
    val threeZoneTariffPeakMaxValue = MutableLiveData<String>()

    val toolbarTariffsTitle = MutableLiveData<String>()

    lateinit var objEnerg: EnergyState

    val isProgress = MutableLiveData<Boolean>().apply { value = true }

    fun setSelelectedRegionId(selelectedRegionId: Int) {
        selelectedRegionIdValue.value = selelectedRegionId
        Log.e("setSelelectedRegionId", "" + selelectedRegionIdValue.value)

        objEnerg = tariffsInteractor.getSelectEnergyState()[selelectedRegionId]

        normalTariffDayValue.value = objEnerg.calculator[0].era_mini + " /"
        normalTariffDayMaxValue.value = objEnerg.calculator[0].era_max + GRN
        normalTariffNightValue.value = objEnerg.calculator[0].era_mini + " /"
        normalTariffNightMaxValue.value = objEnerg.calculator[0].era_max + GRN

        twoZoneTariffDayValue.value = objEnerg.calculator[1].day_mini + " /"
        twoZoneTariffDayMaxValue.value = objEnerg.calculator[1].day_max + GRN
        twoZoneTariffNightValue.value = objEnerg.calculator[1].night_mini + " /"
        twoZoneTariffNightMaxValue.value = objEnerg.calculator[1].night_max + GRN

        threeZoneTariffDayValue.value = objEnerg.calculator[2].day_mini + " /"
        threeZoneTariffDayMaxValue.value = objEnerg.calculator[2].day_max + GRN
        threeZoneTariffNightValue.value = objEnerg.calculator[2].night_mini + " /"
        threeZoneTariffNightMaxValue.value = objEnerg.calculator[2].night_max + GRN
        threeZoneTariffPeakValue.value = objEnerg.calculator[2].peak_mini + " /"
        threeZoneTariffPeakMaxValue.value = objEnerg.calculator[2].peak_max + GRN

        toolbarTariffsTitle.value = objEnerg.name
    }

    init {

        KLog.e("TariffsViewModel", "init")

        isProgress.value = false


        //normalTariffDayValue.value = tariffsInteractor.getSelectEnergyState()[21].calculator[0].era_mini + GRN
//        KLog.e("Tariff", tariffsInteractor.getSelectEnergyState()[selelectedRegionIdValue.value!!].calculator[0].era_mini)

        //KLog.e("Tariff", selelectedRegionIdValue.value)


    }

    companion object {

        private val GRN = " грн"
    }
}