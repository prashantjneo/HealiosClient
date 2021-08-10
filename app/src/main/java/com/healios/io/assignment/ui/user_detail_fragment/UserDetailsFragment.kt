package com.healios.io.assignment.ui.user_detail_fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.healios.io.assignment.R
import com.healios.io.assignment.app_base_component.BaseFragment
import com.healios.io.assignment.databinding.FragmentUserDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : BaseFragment<FragmentUserDetailsBinding>() {

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

    private val userDetailViewModel: UserDetailViewModel by viewModels()

    override fun getLayoutId() = R.layout.fragment_user_details

    override fun onBinding() {
        mBinding.viewModel = userDetailViewModel
        mBinding.lifecycleOwner = this
        getRemoteAllUserDetails()
        getLocalUserDetails()
        setObservers()
    }

    private fun getLocalUserDetails() {
        mBinding.tvPostTitle.text = arguments?.getString(TITLE)
        mBinding.tvPostBody.text = arguments?.getString(BODY)
        userDetailViewModel.getSelectedUserFromLocal(arguments?.getInt(USERID)!!)
        userDetailViewModel.getSelectedUserPostComment(arguments?.getInt(IDES)!!)
    }

    private fun getRemoteAllUserDetails() {
        userDetailViewModel.callRemoteUserDetails()
    }
    private fun setObservers() {
        userDetailViewModel.getLocalUserDetails.observe(viewLifecycleOwner, Observer {
            mBinding.progressbar.visibility = View.GONE

            if (it != null) {
                mBinding.tvUserName.text = it.name
                mBinding.tvUserEmail.text = it.email

            }
        })

        userDetailViewModel.getLocalPostComment.observe(viewLifecycleOwner, Observer {
            mBinding.progressbar.visibility = View.GONE
            if (it != null && it.size != 0) {
                if (it.isNotEmpty()) {
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