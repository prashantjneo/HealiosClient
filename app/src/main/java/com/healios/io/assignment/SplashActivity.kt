package com.healios.io.assignment

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.healios.io.assignment.app_base_component.BaseActivity
import com.healios.io.assignment.databinding.ActivitySplashBinding
import com.healios.io.assignment.ui.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private val SPLASH_TIME_OUT: Long = 2000

    override fun getLayoutId() = R.layout.activity_splash

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