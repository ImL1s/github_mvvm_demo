package com.future.github.users.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.future.github.users.model.GithubUser

class GithubUserItemViewModel(model: GithubUser) {

    // use mutable live data because url need download.(data need change)
    val imageUrlSource = MutableLiveData<String>().apply { value = model.avatarUrl }
    val login = model.login
}