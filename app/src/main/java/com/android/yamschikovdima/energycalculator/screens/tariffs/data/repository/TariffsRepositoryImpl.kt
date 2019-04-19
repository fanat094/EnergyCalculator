package com.android.yamschikovdima.energycalculator.screens.tariffs.data.repository

import android.content.Context
import com.android.yamschikovdima.energycalculator.screens.tariffs.domain.TariffsRepository
import com.android.yamschikovdima.energycalculator.selectenergystate.data.model.EnergyState
import com.android.yamschikovdima.energycalculator.selectenergystate.domain.SelectEnergyStateRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.socks.library.KLog
import io.reactivex.Single

class TariffsRepositoryImpl(

    private val context: Context

) : TariffsRepository {
    override fun getSelectEnergyState(): List<EnergyState> {
        return getEnergyStateList()
    }

    private fun getEnergyStateList(): List<EnergyState> {

        val file_name = "energy_state.json"
        val json_string = context.assets.open(file_name).bufferedReader().use { it.readText() }
        //KLog.e("json_string", "read" + json_string)

        val gson = Gson()
        val listType = object : TypeToken<List<EnergyState>>() {}.type
        val energyStateList = gson.fromJson<List<EnergyState>>(json_string, listType)

        return energyStateList
    }
}