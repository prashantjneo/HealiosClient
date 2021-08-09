package com.healios.io.assignment.utils

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.healios.io.assignment.app_base_component.BaseActivity

var toast: Toast? = null

fun Activity.showMessage(message: String, lengthLong: Boolean = false) {
    if (toast != null) {
        toast?.cancel()
    }
    toast = Toast.makeText(this, message, if (lengthLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT)
    toast?.show()
}

fun Fragment.showMessage(message: String?, lengthLong: Boolean = false) {
    if (toast != null) {
        toast?.cancel()
    }
    toast = Toast.makeText(
        this.activity,
        message,
        if (lengthLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    )
    toast?.show()
}

//Method to replace fragment in the container
fun Fragment.replaceFragment(fragment: Fragment, addToBackStack: Boolean, bundle: Bundle? = null) {
    val activity = this.activity as BaseActivity<*, *>
    activity.replaceFragment(fragment, addToBackStack, bundle)
}