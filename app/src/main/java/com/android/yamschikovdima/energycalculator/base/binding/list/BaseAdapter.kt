package com.android.yamschikovdima.energycalculator.base.binding.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class BaseAdapter(private val viewTypes: BaseViewTypes,
                  private val owner: LifecycleOwner,
                  diffCallback: DiffUtil.ItemCallback<Any>?)
    : ListAdapter<Any, BaseViewHolder>(diffCallback ?: BaseItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder(inflateView(parent, viewTypes.getLayout(viewType)))

    override fun getItemViewType(position: Int) = viewTypes.getViewType(getItem(position)::class)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun inflateView(parent: ViewGroup, layout: Int): View {
        val inflater = LayoutInflater.from(parent.context)

        DataBindingUtil.inflate<ViewDataBinding>(inflater,
            layout,
            parent,
            false)
            ?.apply {
                setLifecycleOwner(owner)
                return root
            }

        return inflater.inflate(
            layout,
            parent,
            false)
    }
}