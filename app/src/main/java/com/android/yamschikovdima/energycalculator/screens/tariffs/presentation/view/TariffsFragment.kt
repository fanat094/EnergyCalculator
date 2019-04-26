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
import com.android.yamschikovdima.energycalculator.base.binding.setVisibility
import com.android.yamschikovdima.energycalculator.base.data.ISharedPreferenceManager
import com.android.yamschikovdima.energycalculator.base.di.appComponent
import com.android.yamschikovdima.energycalculator.databinding.TariffsFragmentBinding
import com.android.yamschikovdima.energycalculator.screens.tariffs.di.DaggerTariffsComponent
import com.android.yamschikovdima.energycalculator.screens.tariffs.di.TariffsModule
import com.android.yamschikovdima.energycalculator.screens.tariffs.presentation.viewmodel.TariffsViewModel
import com.socks.library.KLog
import kotlinx.android.synthetic.main.progress.*
import kotlinx.android.synthetic.main.tariffs_fragment.*
import javax.inject.Inject
import androidx.appcompat.app.AppCompatActivity



class TariffsFragment : Fragment() {

    @Inject
    lateinit var viewModel: TariffsViewModel

    @Inject
    lateinit var preferences: ISharedPreferenceManager

    val component by lazy {
        DaggerTariffsComponent.builder()
            .appComponent(appComponent())
            .tariffsModule(TariffsModule(this))
            .build()
    }

    private val selelectedRegionId by lazy {

        preferences.getIdSelectedEnergyState()
    }

    override fun onAttach(context: Context?) {
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

            KLog.e("toolbar_tariffs", selelectedRegionId)
        }
    }
}