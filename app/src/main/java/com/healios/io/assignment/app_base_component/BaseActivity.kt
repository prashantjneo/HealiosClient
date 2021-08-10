package com.healios.io.assignment.app_base_component

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.healios.io.assignment.R

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity(),
    UICallbacks {

    protected lateinit var mBinding: T
    protected lateinit var mContext: Context

    protected lateinit var mManager: FragmentManager
        private set


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this@BaseActivity, getLayoutId())
        mManager = supportFragmentManager
        mContext = this@BaseActivity
        onBinding()
    }


    /**
     * Method to replace fragment in the container
     */
    fun replaceFragment(fragment: Fragment, addToBackstack: Boolean, bundle: Bundle? = null) {
        bundle?.let {
            fragment.arguments = bundle
        }

        mManager.beginTransaction().apply {
            replace(R.id.container, fragment, fragment.javaClass.name)

            if (addToBackstack) {
                addToBackStack(fragment::class.java.simpleName)
            }
            commit()
        }
    }

}