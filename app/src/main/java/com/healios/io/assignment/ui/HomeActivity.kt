package com.healios.io.assignment.ui

import com.healios.io.assignment.R
import com.healios.io.assignment.app_base_component.BaseActivity
import com.healios.io.assignment.databinding.ActivityHomeBinding
import com.healios.io.assignment.ui.homefragment.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun onBinding() {
        replaceFragment(
            fragment = HomeFragment.newInstance(), addToBackstack = false, bundle = null)
    }

}