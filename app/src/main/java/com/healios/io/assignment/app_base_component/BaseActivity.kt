package com.healios.io.assignment.app_base_component

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.healios.io.assignment.R

abstract class BaseActivity<T :ViewDataBinding,V:BaseViewModel>: AppCompatActivity(),UICallbacks<V> {

    protected lateinit var mBinding: T
    protected lateinit var mViewModel: V
    protected lateinit var mContext: Context

    protected lateinit var mManager: FragmentManager
        private set


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this@BaseActivity, getLayoutId())
        mViewModel =ViewModelProvider(this@BaseActivity).get(getViewModel())
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