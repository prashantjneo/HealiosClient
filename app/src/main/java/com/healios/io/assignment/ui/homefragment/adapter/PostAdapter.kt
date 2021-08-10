package com.healios.io.assignment.ui.homefragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.healios.io.assignment.database.posts.LocalPost
import com.healios.io.assignment.databinding.ListItemPostBinding

class PostAdapter(val listener: OnClickListener, var postList: List<LocalPost>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    var postListOne: List<LocalPost> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ListItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(postList[position], listener)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    class PostViewHolder(private var binding: ListItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LocalPost?, listener: OnClickListener) {
            binding.run {
                data = item
                clickListener = listener
                executePendingBindings()
            }
        }
    }

    interface OnClickListener {
        fun onPostClick(data: LocalPost)
    }

}