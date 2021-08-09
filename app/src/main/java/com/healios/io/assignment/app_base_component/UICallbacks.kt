package com.healios.io.assignment.app_base_component

import androidx.annotation.LayoutRes

interface UICallbacks<V> {

    @LayoutRes
    fun getLayoutId(): Int

    fun getViewModel(): Class<V>

    fun onBinding()
}