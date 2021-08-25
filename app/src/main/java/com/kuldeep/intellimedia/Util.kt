package com.kuldeep.intellimedia

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Author by Kuldeep Makwana, Email kuldeepmakwana3977@gmail.com, Date on 25-08-2021.
 * PS: Not easy to write code, please indicate.
 */

fun hideKeyboard(activity: Activity?) {
    if (activity != null) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        // Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        // If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}