package com.future.github.users.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.future.github.R
import com.future.github.databinding.GithubUserItemBinding
import com.future.github.users.model.GithubUser
import com.future.github.users.viewmodel.GithubUserItemViewModel
import com.future.github.users.viewmodel.GithubUsersViewModel

class GithubUsersAdapter(var githubUsers: MutableList<GithubUser>) :
    RecyclerView.Adapter<GithubUsersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<GithubUserItemBinding>(
            inflater,
            R.layout.github_user_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return githubUsers.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = githubUsers[position]
        holder.apply {
            bind(GithubUserItemViewModel(user))
        }
    }

    class ViewHolder(private val binding: GithubUserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(vm: GithubUserItemViewModel) {
            binding.vm = vm
            binding.executePendingBindings()
        }
    }
}