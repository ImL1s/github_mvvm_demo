package com.future.github.users.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.future.github.BR
import com.future.github.R
import com.future.github.users.viewmodel.GithubUsersViewModel

class GitHubUsersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vm = ViewModelProviders
            .of(this)
            .get(GithubUsersViewModel::class.java)

        val binding =
            DataBindingUtil
                .setContentView<ViewDataBinding>(this, R.layout.activity_git_hub_users)

        binding.setVariable(BR.vm, vm)
        binding.lifecycleOwner = this

    }
}
