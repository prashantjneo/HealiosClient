package com.healios.io.assignment.ui.homefragment

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.healios.io.assignment.R
import com.healios.io.assignment.app_base_component.BaseFragment
import com.healios.io.assignment.database.posts.LocalPost
import com.healios.io.assignment.databinding.FragmentHomeBinding
import com.healios.io.assignment.ui.homefragment.adapter.PostAdapter
import com.healios.io.assignment.ui.user_detail_fragment.UserDetailsFragment
import com.healios.io.assignment.utils.replaceFragment


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    private lateinit var mAdapter: PostAdapter

    var list: ArrayList<LocalPost> = ArrayList()

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun getLayoutId() = R.layout.fragment_home

    override fun getViewModel() = HomeViewModel::class.java

    override fun onBinding() {

        mBinding.run {
            lifecycleOwner = viewLifecycleOwner
            viewModel = mViewModel
        }
        setUpRecylcerView(mBinding.rvPostList)
        getPosts()
        getComment()
        observePosts()
    }

    private fun getComment() {

        mViewModel.callRemotePostComment()
    }

    private fun setUpRecylcerView(rvPostList: RecyclerView) {

        mAdapter = PostAdapter(object : PostAdapter.OnClickListener {
            override fun onPostClick(data: LocalPost) {

                replaceFragment(
                    fragment = UserDetailsFragment.newInstance(
                        data.UserId!!, data.Id!!, data.Title!!, data.Body!!
                    ), addToBackStack = true, bundle = null
                )
            }

        }, list)
        rvPostList.run {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

            this.adapter = mAdapter
        }


    }


    private fun getPosts() {
        mViewModel.callRemotePosts()
    }

    private fun observePosts() {


        mViewModel.getLocalPosts().observe(viewLifecycleOwner, Observer {
            mBinding.progressbar.visibility = View.GONE
            if (it.size != 0)
                list.clear()
            list.addAll(it)
            mAdapter.notifyDataSetChanged()

        })
    }


}