package com.android.yamschikovdima.energycalculator.selectenergystate.presentation.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsSingleChoice
import com.android.yamschikovdima.energycalculator.R
import com.android.yamschikovdima.energycalculator.base.di.appComponent
import com.android.yamschikovdima.energycalculator.databinding.SelectEnergyStateActivityBinding
import com.android.yamschikovdima.energycalculator.screens.main.presentation.view.MainActivity
import com.android.yamschikovdima.energycalculator.selectenergystate.data.model.EnergyState
import com.android.yamschikovdima.energycalculator.selectenergystate.di.DaggerSelectEnergyStateComponent
import com.android.yamschikovdima.energycalculator.selectenergystate.di.SelectEnergyStateModule
import com.android.yamschikovdima.energycalculator.selectenergystate.presentation.router.SelectEnergyStateRouter
import com.android.yamschikovdima.energycalculator.selectenergystate.presentation.viewmodel.SelectEnergyStateViewModel
import com.android.yamschikovdima.energycalculator.utils.PermissionHelper
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import com.socks.library.KLog
import javax.inject.Inject
import com.google.gson.GsonBuilder
import com.socks.library.klog.JsonLog
import com.google.gson.reflect.TypeToken
import io.reactivex.Single


class SelectEnergyStateActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: SelectEnergyStateViewModel

    @Inject
    lateinit var router: SelectEnergyStateRouter

    private var debugMode = false

    val component by lazy {
        DaggerSelectEnergyStateComponent.builder()
            .appComponent(appComponent())
            .selectEnergyStateModule(SelectEnergyStateModule(this))
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.select_energy_state_activity)

        component.inject(this)
        val binding = DataBindingUtil.setContentView<SelectEnergyStateActivityBinding>(
            this,
            com.android.yamschikovdima.energycalculator.R.layout.select_energy_state_activity
        )

        //setEnergyStateList()

        binding.apply {
            lifecycleOwner.let {
                this@SelectEnergyStateActivity
            }
            vm = viewModel

            //events
            viewModel.fabSearchEnergyState.observe(lifecycleOwner.let {
                this@SelectEnergyStateActivity
            }, Observer {

                //fabSearchEnergyStateDialog()
                //getLocationFused()
                setupPermissions()
            })

        }

        router.bindViewModel(viewModel)
    }

    /*fun fabSearchEnergyState() {

//        KLog.e("fabSearchEnergyState", "Click")
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
//        finish()

        val file_name = "energy_state.json"
        val json_string = application.assets.open(file_name).bufferedReader().use {
            it.readText()
        }
        KLog.e("json_string", "read" + json_string)

        val gson = Gson()
        val listType = object : TypeToken<List<EnergyState>>() {}.type
        val newList = gson.fromJson<List<EnergyState>>(json_string, listType)

        KLog.json("responseObject", "------->" + newList)
    }

    private fun setEnergyStateList() {

        /*val file_name = "energy_state.json"
        val json_string = application.assets.open(file_name).bufferedReader().use { it.readText() }
        KLog.e("json_string", "read" + json_string)

        val gson = Gson()
        val listType = object : TypeToken<List<EnergyState>>() {}.type
        val energyStateList = gson.fromJson<List<EnergyState>>(json_string, listType)

        KLog.json("responseObject", "------->" + energyStateList)*/
        //viewModel.setEnergyStateList(energyStateList)
    }*/

     fun fabSearchEnergyStateDialog() {

        this.let {
            MaterialDialog(it).show {
                title(R.string.title_select_energy_state_cancel_selected)
                message(R.string.title_select_energy_state_message)
                positiveButton(R.string.title_select_energy_state_ok)
                negativeButton(R.string.title_select_energy_state_cancel)
                debugMode(debugMode)
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun getLocationFused() {
                val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
                fusedLocationClient.lastLocation
                    .addOnSuccessListener {
                        if (it != null) {
                            val geocoder = Geocoder(this)
                            val address = geocoder?.getFromLocation(it.latitude, it.longitude, 1)
                            KLog.e("fused",address[0].adminArea)
                        }
                    }

    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.RECORD_AUDIO)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Permission to record denied")
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.INTERNET),
            REQUEST_PERMISSIONS_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        when (requestCode) {
            REQUEST_PERMISSIONS_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Log.i(TAG, "Permission has been denied by user")
                } else {
                    Log.i(TAG, "Permission has been granted by user")
                    getLocationFused()

                }
            }
        }
    }

    companion object {

        private val TAG = "LocationProvider"

        private val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    }
}