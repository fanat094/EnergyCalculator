package com.android.yamschikovdima.energycalculator.utils

import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

private const val DEBOUNCE_TIME = 300.toLong()

class LoginFormValidator(private val context : CoroutineContext) {

    private val emailError = MutableLiveData<Boolean>()
    val getEmailError : LiveData<Boolean> get() = emailError

    private val passwordError = MutableLiveData<Boolean>()
    val getPasswordError : LiveData<Boolean> get() = passwordError

    private val formValidation = MutableLiveData<Boolean>()
    val getFormValidation : LiveData<Boolean> get() = formValidation


    val emailTextWatcher = object : TextWatcher {

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

                emailError.value = isEmailValid(changedText)
                formValidation.value = emailError.value ?: false && passwordError.value ?: false
            }
        }
    }


    val passwordTextWatcher = object : TextWatcher {

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

                passwordError.value = changedText.length >= 5
                formValidation.value = emailError.value ?: false && passwordError.value ?: false
            }
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}