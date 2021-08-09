package com.healios.io.assignment

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.healios.io.assignment.app_base_component.BaseActivity
import com.healios.io.assignment.databinding.ActivitySplashBinding
import com.healios.io.assignment.ui.HomeActivity


class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {
    private val SPLASH_TIME_OUT: Long = 2000

    override fun getLayoutId() = R.layout.activity_splash

    override fun getViewModel() = SplashViewModel::class.java

    override fun onBinding() {
        Handler(Looper.getMainLooper()).postDelayed({
            goToHomeActivity()
        }, SPLASH_TIME_OUT)

    }

    private fun goToHomeActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }


}