package com.android.yamschikovdima.energycalculator.screens.costs.presentation.view


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.android.yamschikovdima.energycalculator.R
import com.android.yamschikovdima.energycalculator.base.di.appComponent
import com.android.yamschikovdima.energycalculator.databinding.CostsFragmentBinding
import com.android.yamschikovdima.energycalculator.screens.costs.di.CostsComponent
import com.android.yamschikovdima.energycalculator.screens.costs.di.CostsModule
import com.android.yamschikovdima.energycalculator.screens.costs.di.DaggerCostsComponent
import com.android.yamschikovdima.energycalculator.screens.costs.presentation.viewmodel.CostsViewModel
import javax.inject.Inject


class CostsFragment : Fragment() {

    @Inject
    lateinit var viewModel: CostsViewModel

    val component:CostsComponent by lazy {
        DaggerCostsComponent.builder()
            .appComponent(appComponent())
            .costsModule(CostsModule(this))
            .build()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("CostsFragment", "CostsFragment")

//        return inflater.inflate(R.layout.costs_fragment, container,false)
        return DataBindingUtil.inflate<CostsFragmentBinding>(inflater, R.layout.costs_fragment, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        DataBindingUtil.getBinding<CostsFragmentBinding>(view)?.apply {
            lifecycleOwner.let { this@CostsFragment }
            vm = viewModel
        }
    }
}
