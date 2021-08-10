package com.healios.io.assignment.app_base_component

import androidx.annotation.LayoutRes

interface UICallbacks {

    @LayoutRes
    fun getLayoutId(): Int


    fun onBinding()
}