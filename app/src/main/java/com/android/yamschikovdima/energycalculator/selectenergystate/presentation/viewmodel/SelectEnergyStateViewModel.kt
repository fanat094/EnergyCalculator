package com.android.yamschikovdima.energycalculator.selectenergystate.presentation.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import com.android.yamschikovdima.energycalculator.base.BaseViewModel
import com.android.yamschikovdima.energycalculator.base.SingleLiveEvent
import com.android.yamschikovdima.energycalculator.selectenergystate.data.model.EnergyState
import com.android.yamschikovdima.energycalculator.selectenergystate.domain.SelectEnergyStateInteractor

import com.socks.library.KLog

class SelectEnergyStateViewModel(

    private val selectEnergyStateInteractor: SelectEnergyStateInteractor

) : BaseViewModel() {

    val isProgress = MutableLiveData<Boolean>()
    val fusedRegionEnergyStateData = MutableLiveData<String>()
    val fusedRegionEnergyStateDone = MutableLiveData<String>()
    val fusedRegionEnergyStateObj = MutableLiveData<EnergyState>()

    private fun createItem(energyState: EnergyState) = SelectEnergyStateItemViewModel(energyState).apply {

        click.observe(this@SelectEnergyStateViewModel, Observer {
            itemClick.value = energyState
            KLog.e("itemClickitemClick", energyState.name)
        })
    }

    val items = MutableLiveData<List<SelectEnergyStateItemViewModel>>()
    val itemClick = SingleLiveEvent<EnergyState>()

    val fabSearchEnergyState = SingleLiveEvent<Unit>()

    fun fabSearchEnergyStateEvent() {

        isProgress.postValue(true)
        fabSearchEnergyState.postValue(Unit)
    }

//    fun setEnergyStateList(energyStateList: List<EnergyState>) {
//        KLog.json("energyStateList", "------->" + energyStateList)
//        items.postValue(energyStateList.map { createItem(it) })
//        items.value = energyStateList.map { createItem(it) }
//
//        KLog.e("energyStateList22", "------->" + (items.value))
//    }

//    fun setFusedRegionEnergyState(fusedRegionEnergyState: String?) {
////        fusedRegionEnergyStateData.value = fusedRegionEnergyState
////        Log.e("cityProfileActual", fusedRegionEnergyStateData.value.toString())
//
//        Log.e("regionFused",
//            selectEnergyStateInteractor.getFusedEnergyState(fusedRegionEnergyStateData.value.toString()))
//
//        fusedRegionEnergyStateDone.postValue(selectEnergyStateInteractor
//            .getFusedEnergyState(fusedRegionEnergyStateData.value.toString()))
//
//        //isProgress.value = false
//
//    }

    fun setFusedRegionEnergyState2(fusedRegionEnergyState: String) {

        fusedRegionEnergyStateData.value = fusedRegionEnergyState

        Log.e("setFRegionEnergyState2",
            selectEnergyStateInteractor.getFusedEnergyState2(fusedRegionEnergyState).name)

        fusedRegionEnergyStateObj.postValue(selectEnergyStateInteractor.getFusedEnergyState2(

            fusedRegionEnergyStateData.value.toString()
        ))
    }

    init {

        KLog.e("SelectEnergyStateViewModel", "init")

        //items.postValue(energyStateList.map { createItem(it) })
        items.value = selectEnergyStateInteractor.getSelectEnergyState().map { createItem(it) }
        //selectEnergyStateInteractor.getSelectEnergyState()
    }
}