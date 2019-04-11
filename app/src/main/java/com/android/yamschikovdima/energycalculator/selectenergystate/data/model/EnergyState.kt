package com.android.yamschikovdima.energycalculator.selectenergystate.data.model

data class EnergyState(
    val id: Int,
    val name: String,
    val calculator: List<Calculator>,
    val region:String
)

data class Calculator(
    val id_calculator: Int,
    val era_mini: String,
    val era_max: String,
    val day_mini: String,
    val night_mini: String,
    val day_max: String,
    val night_max: String,
    val peak_mini: String,
    val peak_max: String
)