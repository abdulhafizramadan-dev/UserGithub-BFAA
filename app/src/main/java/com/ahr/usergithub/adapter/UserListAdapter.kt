package com.ahr.usergithub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahr.usergithub.data.User
import com.ahr.usergithub.databinding.ItemUserGithubBinding
import com.bumptech.glide.Glide

class UserListAdapter(private val onItemClickListener: OnItemClickListener) : ListAdapter<User, UserListAdapter.UserViewHolder>(UserDiffCallback) {

    private companion object UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    inner class UserViewHolder(private val binding: ItemUserGithubBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.tvUserName.text = user.name
            binding.tvUserLocation.text = user.location
            binding.tvUserRepositories.text = user.publicRepos.toString()
            binding.tvUserFollowers.text = user.followers.toString()
            binding.tvUserFollowing.text = user.following.toString()

            Glide.with(binding.ivUser)
                .load(user.avatarUrl)
                .into(binding.ivUser)

            itemView.setOnClickListener { onItemClickListener.onItemClickListener(user) }
            binding.btnUserShare.setOnClickListener { onItemClickListener.onBtnShareClicked(user) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserGithubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface OnItemClickListener {
        fun onBtnShareClicked(user: User)
        fun onItemClickListener(user: User)
    }
}