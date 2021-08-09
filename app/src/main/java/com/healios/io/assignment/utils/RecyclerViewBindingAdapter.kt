package com.healios.io.assignment.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.healios.io.assignment.database.posts.LocalPost
import com.healios.io.assignment.ui.homefragment.adapter.PostAdapter


@BindingAdapter("listPost")
fun bindingPostList(recyclerView: RecyclerView, data: List<LocalPost>?) {
    if (data != null) {
        val adapter = recyclerView.adapter as PostAdapter
        adapter.postListOne = data
    }
}