package com.android.yamschikovdima.energycalculator.selectenergystate.data.repository

import android.content.Context
import com.android.yamschikovdima.energycalculator.selectenergystate.data.model.EnergyState
import com.android.yamschikovdima.energycalculator.selectenergystate.domain.SelectEnergyStateRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.socks.library.KLog
import io.reactivex.Single

class SelectEnergyStateRepositoryImpl(

    private val context: Context

) : SelectEnergyStateRepository {
    override fun getFusedEnergyState2(fusedRegion: String): EnergyState {
        return getSearchFusedEnergyState2(fusedRegion)
    }

    override fun getFusedEnergyState(fusedRegion: String): String {
        KLog.e("filterMap_fusedRegion", fusedRegion)

        return getSearchFusedEnergyState(fusedRegion)
    }

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

    private fun getSearchFusedEnergyState(fusedRegion: String): String {

        val energyStateList = getEnergyStateList()
        for (i in 0 until energyStateList.size - 1) {
            val resRegion = energyStateList[i].region.contains(fusedRegion)
            if (resRegion) {
                return energyStateList[i].name
            }
        }
        return ""
    }

    private fun getSearchFusedEnergyState2(fusedRegion: String): EnergyState {

        val energyStateList = getEnergyStateList()
        for (i in 0 until energyStateList.size - 1) {
            val resRegion = energyStateList[i].region.contains(fusedRegion)
            if (resRegion) {
                return energyStateList[i]
            }
        }
        return energyStateList[0]
    }
}