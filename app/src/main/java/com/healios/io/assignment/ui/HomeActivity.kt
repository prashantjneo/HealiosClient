package com.healios.io.assignment.ui

import com.healios.io.assignment.R
import com.healios.io.assignment.app_base_component.BaseActivity
import com.healios.io.assignment.databinding.ActivityHomeBinding
import com.healios.io.assignment.ui.homefragment.HomeFragment

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun getViewModel(): Class<HomeActivityViewModel> {
        return HomeActivityViewModel::class.java
    }

    override fun onBinding() {

        replaceFragment(
            fragment = HomeFragment.newInstance(), addToBackstack = false, bundle = null
        )
    }

}