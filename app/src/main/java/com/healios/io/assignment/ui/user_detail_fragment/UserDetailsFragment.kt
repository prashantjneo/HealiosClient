package com.healios.io.assignment.ui.user_detail_fragment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.healios.io.assignment.R
import com.healios.io.assignment.app_base_component.BaseFragment
import com.healios.io.assignment.databinding.FragmentUserDetailsBinding
import com.healios.io.assignment.utils.showMessage


class UserDetailsFragment : BaseFragment<FragmentUserDetailsBinding, UserDetailViewModel>() {

    companion object {
        fun newInstance(userId: Int, IDS: Int, title: String, body: String) = UserDetailsFragment()
            .apply {
                arguments = Bundle().apply {
                    putInt(USERID, userId)
                    putInt(IDES, IDS)
                    putString(TITLE, title)
                    putString(BODY, body)
                }
            }

        private const val USERID = "userid"
        private const val IDES = "ids"
        private const val TITLE = "title"
        private const val BODY = "body"
    }

    override fun getLayoutId() = R.layout.fragment_user_details

    override fun getViewModel() = UserDetailViewModel::class.java

    override fun onBinding() {

        mBinding.run {
            lifecycleOwner = viewLifecycleOwner
            viewModel = mViewModel

        }
        getRemoteAllUserDetails()
        getLocalUserDetails()
        setObservers()
    }

    private fun getLocalUserDetails() {
        mBinding.tvPostTitle.text = arguments?.getString(TITLE)
        mBinding.tvPostBody.text = arguments?.getString(BODY)
        mViewModel.getSelectedUserFromLocal(arguments?.getInt(USERID)!!)
        mViewModel.getSelectedUserPostComment(arguments?.getInt(IDES)!!)


    }

    private fun getRemoteAllUserDetails() {
        mViewModel.callRemoteUserDetails()
    }


    private fun setObservers() {


        mViewModel.getLocalUserDetails.observe(viewLifecycleOwner, Observer {
            mBinding.progressbar.visibility = View.GONE

            if (it != null) {
                mBinding.tvUserName.text = it.name
                mBinding.tvUserEmail.text = it.email

            }
        })

        mViewModel.getLocalPostComment.observe(viewLifecycleOwner, Observer {
            mBinding.progressbar.visibility = View.GONE

            if (it != null && it.size != 0) {
                showMessage("total comment" + it.size)

                if(it.isNotEmpty()){
                    var comments: String = ""
                    var commentNumber = 1
                    it.forEach { comment ->
                        comments += commentNumber.toString() + ". " + comment.name + "\n"
                        commentNumber++
                    }
                    mBinding.tvCommentsValue.text = comments
                }
            }
        })
    }
}