package com.android.yamschikovdima.energycalculator.selectenergystate.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.android.yamschikovdima.energycalculator.base.BaseViewModel
import com.android.yamschikovdima.energycalculator.selectenergystate.data.model.EnergyState
import com.socks.library.KLog
import ru.misolutions.apteka.base.SingleLiveEvent
import java.text.SimpleDateFormat
import java.util.*

class SelectEnergyStateItemViewModel(private val energyState: EnergyState) : BaseViewModel() {
    val name = MutableLiveData<String>().apply {
        value = energyState.name.toString()
        KLog.e("energyStateList33", value)
    }

    val click = SingleLiveEvent<Unit>()

    fun click() {
        click.value = Unit
    }
}