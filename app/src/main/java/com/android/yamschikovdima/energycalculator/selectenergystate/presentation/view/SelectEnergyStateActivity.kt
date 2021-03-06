package com.android.yamschikovdima.energycalculator.selectenergystate.presentation.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.android.yamschikovdima.energycalculator.R
import com.android.yamschikovdima.energycalculator.base.binding.setVisibility
import com.android.yamschikovdima.energycalculator.base.data.ISharedPreferenceManager
import com.android.yamschikovdima.energycalculator.base.di.appComponent
import com.android.yamschikovdima.energycalculator.databinding.SelectEnergyStateActivityBinding
import com.android.yamschikovdima.energycalculator.screens.main.presentation.view.MainActivity
import com.android.yamschikovdima.energycalculator.selectenergystate.data.model.EnergyState
import com.android.yamschikovdima.energycalculator.selectenergystate.di.DaggerSelectEnergyStateComponent
import com.android.yamschikovdima.energycalculator.selectenergystate.di.SelectEnergyStateModule
import com.android.yamschikovdima.energycalculator.selectenergystate.presentation.router.SelectEnergyStateRouter
import com.android.yamschikovdima.energycalculator.selectenergystate.presentation.viewmodel.SelectEnergyStateViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.socks.library.KLog
import kotlinx.android.synthetic.main.progress.*
import javax.inject.Inject


class SelectEnergyStateActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: SelectEnergyStateViewModel

    @Inject
    lateinit var router: SelectEnergyStateRouter

    private var debugMode = false

    @Inject
    lateinit var preferences: ISharedPreferenceManager

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
            com.android.yamschikovdima.energycalculator.R.layout.select_energy_state_activity)

        binding.apply {

            lifecycleOwner.let {
                this@SelectEnergyStateActivity
            }

            vm = viewModel

            //events

            viewModel.fabSearchEnergyState.observe(lifecycleOwner.let {
                this@SelectEnergyStateActivity
            }, Observer {

                if (checkNetConnection()) {

                if (hasPermissions()) {
                    isProgressBar.setVisibility(true)
                    getLocationFused()

                } else {
                    requestPermissionWithRationale()
                }
            }
                else{

                    tryAgainCheckNetConnection()
                }
            })

            //fused VModel
//            viewModel.fusedRegionEnergyStateDone.observe(lifecycleOwner.let {
//                this@SelectEnergyStateActivity
//            }, Observer {
//
//                KLog.e("fusedFromVM", it)
//
//                if(it.isNotEmpty()) {
//
//                    fabSearchEnergyStateDialog(it)
//                    vibrator()
//                }
//                else{fabSearchEnergyStateDialogEmpty()}
//
//                isProgressBar.setVisibility(false)
//
//            })

            /////
            viewModel.fusedRegionEnergyStateObj.observe(lifecycleOwner.let {
                this@SelectEnergyStateActivity
            }, Observer {

                KLog.e("fusedFromVM", it)

                if(it!=null) {

                    fabSearchEnergyStateDialog(it)
                    vibrator()
                }
                else{fabSearchEnergyStateDialogEmpty()}

                isProgressBar.setVisibility(false)

            })
        }

        viewModel.itemClick.observe(lifecycle.let {
            this@SelectEnergyStateActivity
        }, Observer {
            toMainActivity(it.id)
        })

        router.bindViewModel(viewModel)
    }

    // --- Permissions ---
    private fun hasPermissions(): Boolean {

        val permissions = arrayOf(
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.VIBRATE
        )

        for (perms in permissions) {
            val res = checkCallingOrSelfPermission(perms)
            if (!(res == PackageManager.PERMISSION_GRANTED)) {
                return false
            }
        }
        return true
    }

    private fun requestPerms() {
        KLog.e("hasPermissions2_2_2", "hasPermissions2_2_2")

        val permissions = arrayOf(
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.VIBRATE
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, PERMISSIONS_REQUEST_CODE)
        }
    }

    private fun requestPermissionWithRationale() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)
            || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)
            || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    ||ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.VIBRATE)
        ) {
            val message = "Storage permission is needed to show files count"
            Snackbar.make(this.findViewById(R.id.appbar), message, Snackbar.LENGTH_LONG)
                .setAction("GRANT", View.OnClickListener {
                    requestPerms()

                }).show()
        } else {
            requestPerms()
        }
    }

    // --- Permissions Result ---
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {

        var allowed = true

        when (requestCode) {
            PERMISSIONS_REQUEST_CODE -> {

                for (res in grantResults) {
                    allowed = allowed && (res == PackageManager.PERMISSION_GRANTED)
                }

                if (allowed) {
                    getLocationFused()
                } else {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                            Toast.makeText(this, "Storage Permissions denied.", Toast.LENGTH_SHORT).show()

                        } else {
                            showNoStoragePermissionSnackbar()
                        }
                    }
                }
            }
        }
    }

    // --- Permissions Settings ---
    private fun showNoStoragePermissionSnackbar() {
        Snackbar.make(this.findViewById(R.id.appbar), "Storage permission isn't granted", Snackbar.LENGTH_LONG)
            .setAction("SETTINGS", View.OnClickListener {

                openApplicationSettings()

                Toast.makeText(
                    applicationContext,
                    "Open Permissions and grant the Storage permission",
                    Toast.LENGTH_SHORT
                ).show()

            }).show()
    }

    private fun openApplicationSettings() {
        val appSettingsIntent = Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse("package:" + packageName)
        )
        startActivityForResult(appSettingsIntent, PERMISSIONS_REQUEST_CODE)
    }

    // --- Location Logic ---
    private fun getLocationFused() {

        KLog.e("fusedTag", "fused_init")

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            // place all code to get the last know location here along with onSuccessListener code
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient.lastLocation
                .addOnSuccessListener {
                    if (it != null) {
                        val geocoder = Geocoder(this)
                        val address = geocoder?.getFromLocation(it.latitude, it.longitude, 1)
                        KLog.e("regionLog", address[0].adminArea)
                        Toast.makeText(
                            applicationContext,
                            "Region: " + address[0].adminArea,
                            Toast.LENGTH_SHORT
                        ).show()

                        //fabSearchEnergyStateDialog(address[0].adminArea)
                        //viewModel.setFusedRegionEnergyState(address[0].adminArea)
                        viewModel.setFusedRegionEnergyState2(address[0].adminArea)
                    }
                }

        } else {

            //request permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPerms()
            }
        }
    }


    // --- Internet Utils ---
    private fun checkNetConnection(): Boolean {

        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }

    private fun tryAgainCheckNetConnection(){

        val message = "No Internet Connection!"
        Snackbar.make(this.findViewById(R.id.appbar), message, Snackbar.LENGTH_LONG).show()
    }

    // --- Dialog Utils ---
    private fun fabSearchEnergyStateDialog(regionStr:EnergyState) {

        this.let {
            MaterialDialog(it).show {
                title(R.string.title_select_energy_state_cancel_selected)
                message(text = regionStr.name)
                positiveButton(R.string.title_select_energy_state_ok) { _ ->
                    KLog.e("Tagg","On positive")
                    toMainActivity(regionStr.id)
                }
                negativeButton(R.string.title_select_energy_state_cancel)
                debugMode(debugMode)
            }
        }
    }

    // --- Dialog Utils ---
    private fun fabSearchEnergyStateDialogEmpty() {

        this.let {
            MaterialDialog(it).show {
                title(R.string.title_select_energy_state_cancel_selected_empty)
                message(R.string.title_select_energy_state_message_empty)
                positiveButton(R.string.title_select_energy_state_ok)
                negativeButton(R.string.title_select_energy_state_cancel)
                debugMode(debugMode)
            }
        }
    }

    private fun vibrator(){
        val vibratorService: Vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibratorService.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE))
        }
        else{
            @Suppress("DEPRECATION")
            vibratorService.vibrate(150)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            getLocationFused()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun toMainActivity(selectedId:Int){

        preferences.setIdSelectedEnergyState(selectedId-1)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        finish()
    }

    companion object {
        private val PERMISSIONS_REQUEST_CODE = 34
    }
}