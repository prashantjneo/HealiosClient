package com.healios.io.assignment.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.healios.io.assignment.app_base_component.BaseActivity


//Method to replace fragment in the container
fun Fragment.replaceFragment(fragment: Fragment, addToBackStack: Boolean, bundle: Bundle? = null) {
    val activity = this.activity as BaseActivity<*>
    activity.replaceFragment(fragment, addToBackStack, bundle)
}