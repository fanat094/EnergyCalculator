package com.android.yamschikovdima.energycalculator.base.data

interface ISharedPreferenceManager {

    fun getCookies(): MutableSet<String>

    fun setCookies(cookies: MutableSet<String>)

    fun setIdSelectedEnergyState(idselectedenergystate: String)

}
