package com.android.yamschikovdima.energycalculator.base.binding

import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods

@Suppress("unused")
@BindingMethods(
        BindingMethod(
                type = com.google.android.material.bottomnavigation.BottomNavigationView::class,
                attribute = "app:onNavigationItemSelected",
                method = "setOnNavigationItemSelectedListener"
        ))
class DataBindingAdapter