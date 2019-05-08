package com.android.yamschikovdima.energycalculator.screens.main.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.android.yamschikovdima.energycalculator.R
import com.android.yamschikovdima.energycalculator.base.di.ComponentHolder
import com.android.yamschikovdima.energycalculator.base.di.appComponent
import com.android.yamschikovdima.energycalculator.base.navigationui.setupWithNavController
import com.android.yamschikovdima.energycalculator.databinding.MainActivityBinding
import com.android.yamschikovdima.energycalculator.screens.main.di.DaggerMainComponent
import com.android.yamschikovdima.energycalculator.screens.main.di.MainComponent
import com.android.yamschikovdima.energycalculator.screens.main.di.MainModule
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), ComponentHolder<MainComponent> {

//    @Inject
//    lateinit var viewModel: MainViewModel
//
//    @Inject
//    lateinit var router: MainRouter

    lateinit var navigation: NavController
    private var currentNavController: LiveData<NavController>? = null

    override val component: MainComponent by lazy {
        DaggerMainComponent.builder()
            .appComponent(appComponent())
            .mainModule(MainModule(this@MainActivity))
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.main_activity)
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val binding = DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)

        Log.e("MainActivity", "MainActivity")
        component.inject(this)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }

//        navigation = Navigation.findNavController(this, R.id.nav_host_container)
//        NavigationUI.setupWithNavController(bottomNavigationView, navigation)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val navGraphIds = listOf(R.navigation.calculate, R.navigation.costs, R.navigation.tariffs)

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_container,
            intent = intent
        )

        // Whenever the selected controller changes, setup the action bar.
//        controller.observe(this, Observer { navController ->
//            setupActionBarWithNavController(navController)
//        })
        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    /**
     * Overriding popBackStack is necessary in this case if the app is started from the deep link.
     */
    override fun onBackPressed() {
        if (currentNavController?.value?.popBackStack() != true) {
            super.onBackPressed()
        }
    }
}

        //bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

//        binding.apply {
//            //setLifecycleOwner(this@MainActivity)
//
//            //this.lifecycleOwner(this@MainActivity)
//
//            lifecycleOwner.let { this@MainActivity }
//            vm = viewModel
//        }
//
//        router.bindViewModel(viewModel)
//}

//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.calculate_section -> {
//                navigation.navigate(R.id.to_calculate_section)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.costs_section -> {
//                navigation.navigate(R.id.to_costs_section)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.tariffs_section -> {
//                navigation.navigate(R.id.tariffs_section)
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//        false
//    }