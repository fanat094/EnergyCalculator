package com.android.yamschikovdima.energycalculator.screens.tariffs.domain

class TariffsInteractor(

    private val repository: TariffsRepository) {

    fun getSelectEnergyState() = repository.getSelectEnergyState()
}