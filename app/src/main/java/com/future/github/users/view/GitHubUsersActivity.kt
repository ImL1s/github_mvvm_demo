package com.future.github.users.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.future.github.BR
import com.future.github.R
import com.future.github.users.viewmodel.GithubUsersViewModel


class GitHubUsersActivity : AppCompatActivity() {

    lateinit var vm: GithubUsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initBinding()
    }

    private fun initViewModel() {
        vm = ViewModelProviders
            .of(this)
            .get(GithubUsersViewModel::class.java)

        vm.alertSource.observe(this, Observer { showAlertDialog(it) })
    }

    private fun initBinding() {
        DataBindingUtil.setContentView<ViewDataBinding>(
            this,
            R.layout.activity_git_hub_users
        ).apply {
            setVariable(BR.vm, vm)
            lifecycleOwner = this@GitHubUsersActivity
        }
    }

    private fun showAlertDialog(content: String) {
        val dialog = AlertDialog.Builder(this)
        dialog.setMessage("Error message: $content")
        dialog.setTitle("Alert")
        dialog.setPositiveButton("OK", null)
        val alertDialog = dialog.create()
        alertDialog.show()
    }


}
