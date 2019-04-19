package com.android.yamschikovdima.energycalculator.screens.tariffs.domain

import com.android.yamschikovdima.energycalculator.selectenergystate.data.model.EnergyState

interface TariffsRepository {

    fun getSelectEnergyState(): List<EnergyState>
}