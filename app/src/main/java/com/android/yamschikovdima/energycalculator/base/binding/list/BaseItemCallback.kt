package com.android.yamschikovdima.energycalculator.base.binding.list

import androidx.recyclerview.widget.DiffUtil

class BaseItemCallback : DiffUtil.ItemCallback<Any>() {

    override fun areItemsTheSame(oldItem: Any, newItem: Any) =
            oldItem === newItem

    override fun areContentsTheSame(oldItem: Any, newItem: Any) =
            oldItem == newItem
}