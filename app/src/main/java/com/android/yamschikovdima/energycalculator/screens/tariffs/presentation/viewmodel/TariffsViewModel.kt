package com.android.yamschikovdima.energycalculator.screens.tariffs.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.android.yamschikovdima.energycalculator.base.BaseViewModel
import com.android.yamschikovdima.energycalculator.screens.tariffs.domain.TariffsInteractor
import com.socks.library.KLog

class TariffsViewModel(

    private val tariffsInteractor: TariffsInteractor

) : BaseViewModel() {

    val normalTariffDayValue = MutableLiveData<String>()

    init {

        normalTariffDayValue.value = tariffsInteractor.getSelectEnergyState()[21].calculator[0].era_mini + GRN
        KLog.e("TariffKhmel", tariffsInteractor.getSelectEnergyState()[21].calculator[0].era_mini)

    }

    companion object {

        private val GRN = " грн"
    }
}