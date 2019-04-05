package com.android.yamschikovdima.energycalculator.base.binding.list

import com.android.yamschikovdima.energycalculator.R
import kotlin.reflect.KClass

open class BaseViewTypes {

    private val viewTypes = ArrayList<Pair<KClass<out Any>, Int>>()

    fun getViewType(clazz: KClass<out Any>) = viewTypes.indexOfFirst { it.first == clazz }

    fun getLayout(viewType: Int) =
            if (viewType >= 0 && viewType < viewTypes.size) {
                viewTypes[viewType].second
            } else {
                R.layout.empty_item_layout
            }

    protected fun addViewType(clazz: KClass<out Any>, layout: Int) {
        viewTypes.add(Pair(clazz, layout))
    }
}