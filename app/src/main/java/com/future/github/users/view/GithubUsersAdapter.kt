package com.future.github.users.view

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.future.github.databinding.GithubUserItemBinding
import com.future.github.users.viewmodel.GithubUserItemViewModel

class GithubUsersAdapter : RecyclerView.Adapter<GithubUsersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class ViewHolder(private val binding: GithubUserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(vm: GithubUserItemViewModel) {
            binding.vm = vm
            binding.executePendingBindings()
        }
    }
}