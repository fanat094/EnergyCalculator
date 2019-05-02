package com.android.yamschikovdima.energycalculator.screens.costs.presentation.view


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.android.yamschikovdima.energycalculator.base.binding.setVisibility
import com.android.yamschikovdima.energycalculator.base.data.ISharedPreferenceManager
import com.android.yamschikovdima.energycalculator.base.di.appComponent
import com.android.yamschikovdima.energycalculator.databinding.CostsFragmentBinding
import com.android.yamschikovdima.energycalculator.databinding.TariffsFragmentBinding
import com.android.yamschikovdima.energycalculator.screens.costs.di.CostsComponent
import com.android.yamschikovdima.energycalculator.screens.costs.di.CostsModule
import com.android.yamschikovdima.energycalculator.screens.costs.di.DaggerCostsComponent
import com.android.yamschikovdima.energycalculator.screens.costs.presentation.viewmodel.CostsViewModel
import javax.inject.Inject


class CostsFragment : Fragment() {

    @Inject
    lateinit var viewModel: CostsViewModel

    val component by lazy {
        DaggerCostsComponent.builder()
            .appComponent(appComponent())
            .costsModule(CostsModule(this))
            .build()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("CostsFragment", "CostsFragment")

        return DataBindingUtil.inflate<CostsFragmentBinding>(inflater,
            com.android.yamschikovdima.energycalculator.R.layout.costs_fragment, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        DataBindingUtil.getBinding<CostsFragmentBinding>(view)?.apply {
            lifecycleOwner.let { this@CostsFragment }
            vm = viewModel
        }
    }
}
