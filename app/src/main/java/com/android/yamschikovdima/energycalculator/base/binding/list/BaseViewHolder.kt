package com.android.yamschikovdima.energycalculator.base.binding.list

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.android.yamschikovdima.energycalculator.BR

class BaseViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    private val binding = DataBindingUtil.getBinding<ViewDataBinding>(itemView)

    fun bind(viewModel: Any) {

        binding?.setVariable(BR.vm, viewModel)
        binding?.executePendingBindings()
    }
}