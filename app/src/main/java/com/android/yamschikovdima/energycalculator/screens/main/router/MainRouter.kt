package com.android.yamschikovdima.energycalculator.screens.main.router

import com.android.yamschikovdima.energycalculator.screens.main.presentation.view.MainActivity

class MainRouter(
        val activity: MainActivity
) {
//    private var current: MainBottomTab? = MainBottomTab.CALCULATE
//    fun bindViewModel(viewModel: MainViewModel) {
//        viewModel.selected.observe(activity, Observer {
//            if (current == it) return@Observer
//            val actionId = when (it) {
//                MainBottomTab.CALCULATE, null -> R.id.to_calculate_section
//                MainBottomTab.COSTS -> R.id.to_costs_section
//                MainBottomTab.TARIFFS -> R.id.to_tariffs_section
//            }
//            activity.navigation.navigate(actionId)
//            current = it
//        })
//    }
}