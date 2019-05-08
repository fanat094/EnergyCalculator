package com.android.yamschikovdima.energycalculator.screens.calculate.presentation.view


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.android.yamschikovdima.energycalculator.R
import com.android.yamschikovdima.energycalculator.base.di.appComponent
import com.android.yamschikovdima.energycalculator.databinding.CalculateFragmentBinding
import com.android.yamschikovdima.energycalculator.screens.calculate.di.CalculateComponent
import com.android.yamschikovdima.energycalculator.screens.calculate.di.CalculateModule
import com.android.yamschikovdima.energycalculator.screens.calculate.di.DaggerCalculateComponent
import com.android.yamschikovdima.energycalculator.screens.calculate.presentation.viewmodel.CalculateViewModel
import com.socks.library.KLog
import kotlinx.android.synthetic.main.calculate_fragment.*
import javax.inject.Inject

class CalculateFragment : Fragment() {

    @Inject
    lateinit var viewModel: CalculateViewModel

//    @Inject
//    lateinit var preferences: ISharedPreferenceManager

    private var debugMode = false

    val component:CalculateComponent by lazy {
        DaggerCalculateComponent.builder()
            .appComponent(appComponent())
            .calculateModule(CalculateModule(this))
            .build()
    }

//    private val selelectedRegionId by lazy {
//
//        preferences.getIdSelectedEnergyState()
//    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Log.e("CalculateFragment", "CalculateFragment")

        return DataBindingUtil.inflate<com.android.yamschikovdima.energycalculator.databinding.CalculateFragmentBinding>(inflater, R.layout.calculate_fragment, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        DataBindingUtil.getBinding<CalculateFragmentBinding>(view)?.apply {

            lifecycleOwner.let { this@CalculateFragment }
            vm = viewModel

            toolbar_calculate.inflateMenu(R.menu.calculate_menu)

            toolbar_calculate.setOnMenuItemClickListener{
                KLog.e("menuItemClickListener", "trueClick")
                tariffDialog()
                true
            }
        }
    }

    //tariffDialog
    fun tariffDialog() {

        context?.let {
            MaterialDialog(it).show {
                title(R.string.title_item_menu_select_tariff)
                listItemsSingleChoice(R.array.tariffsDialogItems, initialSelection = 0) { _, index, text ->
                    viewModel.setChoiceTariff(index)
                    KLog.e("menuItemChoice", index)
//                    normalTariffDayMaxValueTv.visibility.let { true }
                }
                positiveButton(R.string.title_select_energy_state_ok)
                negativeButton(R.string.title_select_energy_state_cancel)
                debugMode(debugMode)
            }
        }
    }
}