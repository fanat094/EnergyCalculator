package com.android.yamschikovdima.energycalculator.screens.tariffs.presentation.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.android.yamschikovdima.energycalculator.base.binding.setVisibility
import com.android.yamschikovdima.energycalculator.base.data.ISharedPreferenceManager
import com.android.yamschikovdima.energycalculator.base.di.appComponent
import com.android.yamschikovdima.energycalculator.databinding.TariffsFragmentBinding
import com.android.yamschikovdima.energycalculator.screens.tariffs.di.DaggerTariffsComponent
import com.android.yamschikovdima.energycalculator.screens.tariffs.di.TariffsComponent
import com.android.yamschikovdima.energycalculator.screens.tariffs.di.TariffsModule
import com.android.yamschikovdima.energycalculator.screens.tariffs.presentation.viewmodel.TariffsViewModel
import kotlinx.android.synthetic.main.progress.*
import javax.inject.Inject


class TariffsFragment : Fragment(), LifecycleOwner {

    @Inject
    lateinit var viewModel: TariffsViewModel

    @Inject
    lateinit var preferences: ISharedPreferenceManager

    val component:TariffsComponent by lazy {
        DaggerTariffsComponent.builder()
            .appComponent(appComponent())
            .tariffsModule(TariffsModule(this))
            .build()
    }

    private val selelectedRegionId by lazy {

        preferences.getIdSelectedEnergyState()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
        viewModel.setSelelectedRegionId(selelectedRegionId)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("TariffsFragment", "TariffsFragment")
        return DataBindingUtil.inflate<TariffsFragmentBinding>(inflater, com.android.yamschikovdima.energycalculator.R.layout.tariffs_fragment, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        isProgressBar.setVisibility(true)

        DataBindingUtil.getBinding<TariffsFragmentBinding>(view)?.apply {

            lifecycleOwner.let { this@TariffsFragment }
            vm = viewModel

            //KLog.e("toolbar_tariffs", selelectedRegionId)
        }
    }
}