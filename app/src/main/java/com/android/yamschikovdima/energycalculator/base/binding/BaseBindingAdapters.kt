package com.android.yamschikovdima.energycalculator.base.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import com.android.yamschikovdima.energycalculator.base.binding.list.BaseAdapter
import com.android.yamschikovdima.energycalculator.base.binding.list.BaseViewTypes


@BindingAdapter(value = ["items", "viewTypes", "itemDecoration", "diffCallback"])
fun setItems(view: androidx.recyclerview.widget.RecyclerView,
             items: List<Any>?,
             viewTypes: BaseViewTypes,
             itemDecoration: androidx.recyclerview.widget.RecyclerView.ItemDecoration?,
             diffCallback: DiffUtil.ItemCallback<Any>?) {
    if (view.adapter == null) {
        view.adapter = BaseAdapter(viewTypes, view.context as LifecycleOwner, diffCallback)
        if (itemDecoration != null) view.addItemDecoration(itemDecoration)
    }
    if (items != null) {
        (view.adapter as BaseAdapter).submitList(items)
    }
}

@BindingAdapter(value = ["items", "viewTypes"])
fun setItems(view: androidx.recyclerview.widget.RecyclerView,
             items: List<Any>?,
             viewTypes: BaseViewTypes) {
    setItems(view, items, viewTypes, null, null)
}

@BindingAdapter("visibility")
fun View.setVisibility(value: Boolean?) {
    visibility = if (value != false) View.VISIBLE else View.GONE
}