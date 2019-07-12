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
import com.android.yamschikovdima.energycalculator.base.data.ISharedPreferenceManager
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

    @Inject
    lateinit var preferences: ISharedPreferenceManager

    private var debugMode = false

    val component: CalculateComponent by lazy {
        DaggerCalculateComponent.builder()
            .appComponent(appComponent())
            .calculateModule(CalculateModule(this))
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
        Log.e("CalculateFragmentInit", "CalculateFragment")

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
                showTariffDialog()
                true
            }

            normalTariffDayValueTv.addTextChangedListener(viewModel.mNormalTariffDayValueFormValidator.fieldTextWatcher)
            normalTariffNightValueTv.addTextChangedListener(viewModel.mNormalTariffNightValueFormValidator.fieldTextWatcher)

        }

        viewModel.mNormalTariffDayValueFormValidator.getFieldError.observe(this, Observer { error ->
            if (!error) {
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

        viewModel.mNormalTariffNightValueFormValidator.getFieldError.observe(this, Observer { error ->
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

            if (!it) {

                KLog.e("goDialog", "init")
                showCorrectPreCurrentValueDialog()
            }
        })
    }

    //checkValid
    private fun checkValidFirstEdit(): Boolean {

        var checkValid = false

        viewModel.mNormalTariffDayValueFormValidator.getFieldError.observe(this, Observer { error ->
            if (!error) {
                normalTariffDayValueTv.error = getString(R.string.validation_message)
                checkValid = false
            } else {
                KLog.e("err", "init")
                normalTariffDayValueTv.error = null
                checkValid = true

                if (checkValidTwoEdit()) {
                    normalTariffBtn.isEnabled = true
                }
            }
        })

        return checkValid
    }

    private fun checkValidTwoEdit(): Boolean {

        var checkValid = false

        viewModel.mNormalTariffNightValueFormValidator.getFieldError.observe(this, Observer { error ->
            if (!error) {
                normalTariffNightValueTv.error = getString(R.string.validation_message)
                //normalTariffBtn.isEnabled = false
                checkValid = false
                KLog.e("checkValid", checkValid)
            } else {

                normalTariffNightValueTv.error = null
                //normalTariffBtn.isEnabled = true
                checkValid = true
                KLog.e("checkValid", normalTariffNightValueTv.text)
            }
        })

        return checkValid
    }

    //tariffDialog
    private fun showTariffDialog() {

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

    private fun showCorrectPreCurrentValueDialog() {

        KLog.e("correctPreCurrent", "init")

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