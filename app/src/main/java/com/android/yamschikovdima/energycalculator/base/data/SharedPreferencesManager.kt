package com.android.yamschikovdima.energycalculator.base.data

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager : ISharedPreferenceManager{
    override fun setIdSelectedEnergyState(idselectedenergystate: String) {
        preferences.edit()
            .putString(PREF_ID_SES, idselectedenergystate)
            .apply()
    }

    val preferences : SharedPreferences


    constructor(context: Context) {
        preferences = context.getSharedPreferences(APTEKA_PREFS, Context.MODE_PRIVATE)
    }

    companion object {
        const val APTEKA_PREFS = "APTEKA_PREFS"
        const val PREF_COOKIES = "PREF_COOKIES"
        const val PREF_ID_SES = "PREF_ID_SES"
    }

    override fun getCookies(): MutableSet<String> {
        return preferences.getStringSet(PREF_COOKIES, mutableSetOf())
    }

    override fun setCookies(cookies: MutableSet<String>) {
        preferences.edit()
                .putStringSet(PREF_COOKIES, cookies)
                .apply()
    }

}