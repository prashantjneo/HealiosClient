package com.healios.io.assignment.ui.homefragment

import android.view.View
import androidx.fragment.app.viewModels
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var mAdapter: PostAdapter
    var list: ArrayList<LocalPost> = ArrayList()
    private  val homeViewModel: HomeViewModel by viewModels()


    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun getLayoutId() = R.layout.fragment_home

    override fun onBinding() {

        mBinding.viewModel = homeViewModel
        mBinding.lifecycleOwner = this
        setUpRecylcerView(mBinding.rvPostList)
        getPosts()
        getComment()
        observePosts()
    }

    private fun getComment() {
        homeViewModel.callRemotePostComment()
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
        homeViewModel.callRemotePosts()
    }
    private fun observePosts() {
        homeViewModel.getLocalPosts().observe(viewLifecycleOwner, Observer {
            mBinding.progressbar.visibility = View.GONE
            if (it.size != 0)
                list.clear()
            list.addAll(it)
            mAdapter.notifyDataSetChanged()
        })
    }
}