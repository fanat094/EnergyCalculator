package com.android.yamschikovdima.energycalculator.utils

import java.text.DecimalFormat

class FormattingUtil {

    private val kilowattShortFormatter = DecimalFormat("###0")
    private val uaHryvnyaShortFormatter = DecimalFormat("###0")

    fun formatKilowattShort(price: Int?): String {
        return kilowattShortFormatter.format(price) + " кВ"
    }

    fun formatKilowattShort(price: Double?): String {
        return kilowattShortFormatter.format(price) + " кВ"
    }

    fun formatUaHryvnyaShort(price: Double?): String {
        return uaHryvnyaShortFormatter.format(price) + " грн"
    }
}