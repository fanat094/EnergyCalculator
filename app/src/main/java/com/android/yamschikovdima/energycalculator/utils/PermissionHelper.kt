package com.android.yamschikovdima.energycalculator.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionHelper {

    fun isPermissionGrantedAndRequest(context: Context, permission: String) : Boolean {
        if (isPermissionGranted(context, permission)) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(context as Activity,
                            permission)) {
            } else {
                ActivityCompat.requestPermissions(context,
                        arrayOf(permission),
                        0x000000f)
            }
        } else {
            return true
        }
        return false
    }

    fun isPermissionGranted(context: Context, permission: String) : Boolean{
        return ContextCompat.checkSelfPermission(context,
                permission) == PackageManager.PERMISSION_GRANTED
    }
}