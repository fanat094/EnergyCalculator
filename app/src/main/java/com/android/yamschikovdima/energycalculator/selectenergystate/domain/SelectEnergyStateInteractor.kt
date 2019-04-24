package com.android.yamschikovdima.energycalculator.selectenergystate.domain

class SelectEnergyStateInteractor(
    private val repository: SelectEnergyStateRepository
) {

    fun getSelectEnergyState() = repository.getSelectEnergyState()

    fun getFusedEnergyState(fusedRegion:String) = repository.getFusedEnergyState(fusedRegion)

    fun getFusedEnergyState2(fusedRegion:String) = repository.getFusedEnergyState2(fusedRegion)
}