package com.android.yamschikovdima.energycalculator.selectenergystate.presentation.viewmodel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations.map
import com.android.yamschikovdima.energycalculator.R
import com.android.yamschikovdima.energycalculator.base.BaseViewModel
import com.android.yamschikovdima.energycalculator.screens.main.router.MainBottomTab
import com.android.yamschikovdima.energycalculator.selectenergystate.data.model.EnergyState
import com.android.yamschikovdima.energycalculator.selectenergystate.domain.SelectEnergyStateInteractor
import com.android.yamschikovdima.energycalculator.utils.PermissionHelper
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.socks.library.KLog
import ru.misolutions.apteka.base.SingleLiveEvent

class SelectEnergyStateViewModel(

    private val selectEnergyStateInteractor: SelectEnergyStateInteractor

) : BaseViewModel() {


    private fun createItem(energyState: EnergyState) = SelectEnergyStateItemViewModel(energyState).apply {

        click.observe(this, Observer {
            itemClick.value = energyState
            KLog.e("itemClickitemClick", energyState.name)
        })
    }

    val items = MutableLiveData<List<SelectEnergyStateItemViewModel>>()
    val itemClick = SingleLiveEvent<EnergyState>()

    val fabSearchEnergyState = SingleLiveEvent<Unit>()

    fun fabSearchEnergyStateEvent() {

        fabSearchEnergyState.postValue(Unit)
    }

//    fun setEnergyStateList(energyStateList: List<EnergyState>) {
//        KLog.json("energyStateList", "------->" + energyStateList)
//        items.postValue(energyStateList.map { createItem(it) })
//        items.value = energyStateList.map { createItem(it) }
//
//        KLog.e("energyStateList22", "------->" + (items.value))
//    }

    init {

        //items.postValue(energyStateList.map { createItem(it) })

        items.value = selectEnergyStateInteractor.getSelectEnergyState().map { createItem(it) }
        //selectEnergyStateInteractor.getSelectEnergyState()
    }
}