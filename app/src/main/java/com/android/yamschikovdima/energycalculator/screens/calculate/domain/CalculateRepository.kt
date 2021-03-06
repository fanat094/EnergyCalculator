package com.android.yamschikovdima.energycalculator.screens.calculate.domain

import com.android.yamschikovdima.energycalculator.selectenergystate.data.model.EnergyState

interface CalculateRepository {

    fun getSelectedEnergyState(fusedRegion: Int): EnergyState
}