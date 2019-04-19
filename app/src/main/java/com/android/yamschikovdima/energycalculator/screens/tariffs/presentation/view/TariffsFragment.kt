package com.android.yamschikovdima.energycalculator.screens.tariffs.presentation.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.android.yamschikovdima.energycalculator.R
import com.android.yamschikovdima.energycalculator.base.di.appComponent
import com.android.yamschikovdima.energycalculator.databinding.TariffsFragmentBinding
import com.android.yamschikovdima.energycalculator.screens.tariffs.di.DaggerTariffsComponent
import com.android.yamschikovdima.energycalculator.screens.tariffs.di.TariffsModule
import com.android.yamschikovdima.energycalculator.screens.tariffs.presentation.viewmodel.TariffsViewModel
import javax.inject.Inject

class TariffsFragment : Fragment() {

    @Inject
    lateinit var viewModel: TariffsViewModel

    val component by lazy {
        DaggerTariffsComponent.builder()
            .appComponent(appComponent())
            .tariffsModule(TariffsModule(this))
            .build()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("TariffsFragment", "TariffsFragment")
        return DataBindingUtil.inflate<TariffsFragmentBinding>(inflater, R.layout.tariffs_fragment, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        DataBindingUtil.getBinding<TariffsFragmentBinding>(view)?.apply {

            lifecycleOwner.let { this@TariffsFragment }
            vm = viewModel
        }
    }

}
