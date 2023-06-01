package com.example.touristapp.util

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object Util {

    fun hideSoftKeyboard(view: View) {
        val keyboard = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        // Not using getCurrentFocus as that sometimes is null, but the keyboard is still up.
        keyboard.hideSoftInputFromWindow(view.windowToken, 0)
    }
}