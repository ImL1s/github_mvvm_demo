package com.future.github.bindingadapters

import android.graphics.Bitmap
import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.future.github.users.model.GithubUser
import de.hdodenhof.circleimageview.CircleImageView

object BindingAdapter {

    @BindingAdapter("users")
    @JvmStatic
    fun setRecyclerView(view: RecyclerView, users: List<GithubUser>?) {
// TODO BindingAdapter setRecyclerView
    }


    @BindingAdapter("image")
    @JvmStatic
    fun setCircleViewImage(view: CircleImageView, url: String){
        Glide.with(view).load(url).centerCrop().into(view)
    }
}