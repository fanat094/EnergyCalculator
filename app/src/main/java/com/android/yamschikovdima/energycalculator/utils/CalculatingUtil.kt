package com.android.yamschikovdima.energycalculator.utils

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

class CalculatingUtil {

    private val kilowattShortFormatter = DecimalFormat("###0")

    fun calculateMinusOperator(normalTariffPreEnergyValue: Int?, normalTariffCurrentEnergyValue: Int?): Int? {
        return normalTariffPreEnergyValue?.let { normalTariffCurrentEnergyValue?.minus(it) }
    }

    fun calculateMinusHundredOperator(differenceValue: Int?): Int? {
        return differenceValue?.minus(HUNDRED)
    }

    fun calculateMinusAfterHundredOperator(selectedRegionEraMax: String?, afterHundred: Int?): Double? {
        return afterHundred?.let { selectedRegionEraMax?.toDouble()?.times(it) }

    }

    fun calculateMinusPreHundredOperator(selectedRegionEraMini: String?): Double? {
        return selectedRegionEraMini?.toDouble()?.times(HUNDRED)
    }

    fun calculateTariffsSumValueOperator(preHundredValue: Double?, afterHundredValue: Double?): Double? {
        return preHundredValue?.let { afterHundredValue?.plus(it) }
    }

    fun calculateTariffsSumValueOperator(differenceDayValue: Int?, differenceNightValue: Int?): Int? {
        return differenceDayValue?.let { differenceNightValue?.plus(it) }
    }

    //2tariff

    fun calculateTariffsPercentValueOperator(differenceValue: Int?, tariffsSumValue: Int?): Int? {

        val resultDoubleFormat = tariffsSumValue?.toDouble()?.let { differenceValue?.toDouble()?.div(it) }
        val resultTimesHundredFormat = resultDoubleFormat?.times(HUNDRED)
        return resultTimesHundredFormat?.let { BigDecimal(it).setScale(0, RoundingMode.HALF_EVEN).toInt() }
    }


    fun calculateDaySpendEnergyPreHundredOperator(selectedRegionDayMini: String?, preHundred: Int?): Double? {
        return preHundred?.let { selectedRegionDayMini?.toDouble()?.times(it) }
    }

    companion object {
        private const val HUNDRED: Int = 100
    }

}