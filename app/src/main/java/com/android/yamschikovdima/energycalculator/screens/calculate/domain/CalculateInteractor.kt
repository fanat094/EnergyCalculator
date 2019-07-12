package com.android.yamschikovdima.energycalculator.screens.calculate.domain

class CalculateInteractor(

    private val repository: CalculateRepository
) {

    fun getSelectedEnergyState(fusedRegion: Int) = repository.getSelectedEnergyState(fusedRegion)
}