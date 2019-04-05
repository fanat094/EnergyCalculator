package com.android.yamschikovdima.energycalculator.selectenergystate.presentation.view

import com.android.yamschikovdima.energycalculator.R
import com.android.yamschikovdima.energycalculator.base.binding.list.BaseViewTypes
import com.android.yamschikovdima.energycalculator.selectenergystate.presentation.viewmodel.SelectEnergyStateItemViewModel

class SelectEnergyStateItemViewTypes private constructor() : BaseViewTypes() {

    init {
        addViewType(SelectEnergyStateItemViewModel::class, R.layout.select_energy_state_item)
    }

    companion object {
        @JvmStatic
        val instance = SelectEnergyStateItemViewTypes()
    }
}