package com.android.yamschikovdima.energycalculator.screens.main.presentation.view

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.yamschikovdima.energycalculator.R
import com.android.yamschikovdima.energycalculator.base.di.ComponentHolder
import com.android.yamschikovdima.energycalculator.base.di.appComponent
import com.android.yamschikovdima.energycalculator.databinding.MainActivityBinding
import com.android.yamschikovdima.energycalculator.screens.main.di.DaggerMainComponent
import com.android.yamschikovdima.energycalculator.screens.main.di.MainComponent
import com.android.yamschikovdima.energycalculator.screens.main.di.MainModule
import com.android.yamschikovdima.energycalculator.screens.main.presentation.viewmodel.MainViewModel
import com.android.yamschikovdima.energycalculator.screens.main.router.MainRouter
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), ComponentHolder<MainComponent> {

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var router: MainRouter

    lateinit var navigation: NavController

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

        component.inject(this)
        val binding = DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)
        navigation = Navigation.findNavController(this, R.id.main_host_fragment)

        binding.apply {
            setLifecycleOwner(this@MainActivity)

            //this.lifecycleOwner(this@MainActivity)
            vm = viewModel
        }

        router.bindViewModel(viewModel)
    }
}