package com.android.yamschikovdima.energycalculator.screens.calculate.data.repository

import android.content.Context
import com.android.yamschikovdima.energycalculator.screens.calculate.domain.CalculateRepository
import com.android.yamschikovdima.energycalculator.selectenergystate.data.model.EnergyState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CalculateRepositoryImpl(

    private val context: Context

) : CalculateRepository {
    override fun getSelectedEnergyState(fusedRegion: Int): EnergyState {
        return getSelectedEnergyStateTarrifs(fusedRegion)
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

    private fun getSelectedEnergyStateTarrifs(fusedRegion: Int): EnergyState {

        val energyStateList = getEnergyStateList()
        return energyStateList[fusedRegion]
    }

}