package com.android.yamschikovdima.energycalculator.selectenergystate.domain
import com.android.yamschikovdima.energycalculator.selectenergystate.data.model.EnergyState
import io.reactivex.Single

interface SelectEnergyStateRepository {

    fun getSelectEnergyState(): List<EnergyState>

    fun getFusedEnergyState(fusedRegion:String): String

    fun getFusedEnergyState2(fusedRegion:String): EnergyState
}