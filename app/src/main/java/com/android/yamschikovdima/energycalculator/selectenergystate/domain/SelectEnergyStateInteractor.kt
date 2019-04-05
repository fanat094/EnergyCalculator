package com.android.yamschikovdima.energycalculator.selectenergystate.domain

class SelectEnergyStateInteractor(
    private val repository: SelectEnergyStateRepository
) {

    fun getSelectEnergyState() = repository.getSelectEnergyState()
}