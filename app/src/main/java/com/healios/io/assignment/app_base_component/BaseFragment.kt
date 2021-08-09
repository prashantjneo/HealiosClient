package com.healios.io.assignment.app_base_component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : Fragment(), UICallbacks<V> {


    protected lateinit var mBinding: T
    protected lateinit var mViewModel: V


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mViewModel = ViewModelProvider(this@BaseFragment).get(getViewModel())

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            onBinding()
    }

}