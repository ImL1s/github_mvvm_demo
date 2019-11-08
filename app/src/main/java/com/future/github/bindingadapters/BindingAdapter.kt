package com.future.github.bindingadapters

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.future.github.users.model.GithubUser

object BindingAdapter {

    @BindingAdapter("users")
    @JvmStatic
    fun setRecyclerView(view: RecyclerView, users: List<GithubUser>?) {
// TODO BindingAdapter setRecyclerView

        Log.e("GG", "user: ${users?.size}")
    }
}