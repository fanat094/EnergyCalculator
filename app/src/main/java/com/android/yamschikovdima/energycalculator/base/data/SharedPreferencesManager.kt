package com.android.yamschikovdima.energycalculator.base.data

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager : ISharedPreferenceManager{
    override fun getIdSelectedEnergyState(): Int {

        return preferences.getInt(PREF_ID_SES, 0)
    }

    override fun setIdSelectedEnergyState(idselectedenergystate: Int) {
        preferences.edit()
            .putInt(PREF_ID_SES, idselectedenergystate)
            .apply()
    }

    val preferences : SharedPreferences


    constructor(context: Context) {
        preferences = context.getSharedPreferences(APTEKA_PREFS, Context.MODE_PRIVATE)
    }

    companion object {
        const val APTEKA_PREFS = "APTEKA_PREFS"
        const val PREF_ID_SES = "PREF_ID_SES"
    }

//    override fun getCookies(): MutableSet<String> {
//        return preferences.getStringSet(PREF_COOKIES, mutableSetOf())
//    }
}