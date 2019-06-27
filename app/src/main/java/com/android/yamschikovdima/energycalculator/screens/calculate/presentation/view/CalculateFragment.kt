package com.android.yamschikovdima.energycalculator.screens.calculate.presentation.view


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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
import kotlinx.android.synthetic.main.calculate_fragment.view.*
import javax.inject.Inject

class CalculateFragment : Fragment() {

    @Inject
    lateinit var viewModel: CalculateViewModel

//    @Inject
//    lateinit var preferences: ISharedPreferenceManager

    private var debugMode = false

    val component: CalculateComponent by lazy {
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

        return DataBindingUtil.inflate<com.android.yamschikovdima.energycalculator.databinding.CalculateFragmentBinding>(
            inflater,
            R.layout.calculate_fragment,
            container,
            false
        ).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        DataBindingUtil.getBinding<CalculateFragmentBinding>(view)?.apply {

            lifecycleOwner.let { this@CalculateFragment }
            vm = viewModel

            toolbar_calculate.inflateMenu(R.menu.calculate_menu)

            toolbar_calculate.setOnMenuItemClickListener {
                KLog.e("menuItemClickListener", "trueClick")
                tariffDialog()
                true
            }

            normalTariffDayValueTv.addTextChangedListener(viewModel.mLoginFormValidator.passwordTextWatcher)
            normalTariffNightValueTv.addTextChangedListener(viewModel.mLoginFormValidator2.passwordTextWatcher)

        }

        viewModel.mLoginFormValidator.getPasswordError.observe(this, Observer { error ->
            if (!error) {
                //KLog.e("!err", "init")
                view.normalTariffDayValueTv.error = "Не менше 5-ти"

                view.normalTariffBtn.isEnabled = false
            } else {
                KLog.e("err", "init")
                view.normalTariffDayValueTv.error = null

                if (checkValidTwoEdit()) {
                    view.normalTariffBtn.isEnabled = true
                }
            }
        })

        viewModel.mLoginFormValidator2.getPasswordError.observe(this, Observer { error ->
            if (!error) {
                view.normalTariffNightValueTv.error = "Не менше 5-ти"
                view.normalTariffBtn.isEnabled = false
            } else {

                view.normalTariffNightValueTv.error = null

                if (checkValidFirstEdit()) {
                    view.normalTariffBtn.isEnabled = true
                }
            }
        })

        //
        viewModel.checkTariffCurrentEnergyValue.observe(this, Observer {

            if (!it){

                KLog.e("goDialog", "init")
                correctPreCurrentValueDialog()
            }
        })
    }


    //checkValid
    private fun checkValidTwoEdit(): Boolean {

        var hh = false

        viewModel.mLoginFormValidator2.getPasswordError.observe(this, Observer { error ->
            if (!error) {
                normalTariffNightValueTv.error = "Не менше 5-ти"
                //normalTariffBtn.isEnabled = false
                hh = false
                KLog.e("checkValid", hh)
            } else {

                normalTariffNightValueTv.error = null
                //normalTariffBtn.isEnabled = true
                hh = true
                KLog.e("checkValid", normalTariffNightValueTv.text)
            }
        })

        return hh
    }

    private fun checkValidFirstEdit(): Boolean {

        var hh = false

        viewModel.mLoginFormValidator.getPasswordError.observe(this, Observer { error ->
            if (!error) {
                normalTariffDayValueTv.error = "Не менше 5-ти"
                hh = false
            } else {
                KLog.e("err", "init")
                normalTariffDayValueTv.error = null
                hh = true

                if (checkValidTwoEdit()) {
                    normalTariffBtn.isEnabled = true
                }
            }
        })

        return hh
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

    fun correctPreCurrentValueDialog(){

        context?.let {
            MaterialDialog(it).show {
                title(R.string.title_pre_current_value)
                message(R.string.message_pre_current_value)
                positiveButton(R.string.title_select_energy_state_ok)
                negativeButton(R.string.title_select_energy_state_cancel)
                debugMode(debugMode)
            }
        }
    }
}