package com.android.yamschikovdima.energycalculator.utils

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

private const val DEBOUNCE_TIME = 300.toLong()

class FormValidator(private val context : CoroutineContext) {

    private val fieldError = MutableLiveData<Boolean>()
    val getFieldError : LiveData<Boolean> get() = fieldError

    private val formValidation = MutableLiveData<Boolean>()

    val fieldTextWatcher = object : TextWatcher {

        private var finalText = ""

        override fun afterTextChanged(s: Editable?) = Unit

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val changedText = s.toString().trim()
            if (changedText == finalText)
                return

            finalText = changedText

            GlobalScope.launch(context){
                delay(DEBOUNCE_TIME)
                if (changedText != finalText)
                    return@launch

                fieldError.value = changedText.length >= MINLENGTHFIELD
                formValidation.value =  fieldError.value ?: false
            }
        }
    }

    companion object {
        private const val MINLENGTHFIELD: Int = 5
    }
}