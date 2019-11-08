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
import kotlinx.android.synthetic.main.activity_git_hub_users.*


class GitHubUsersActivity : AppCompatActivity() {

    lateinit var vm: GithubUsersViewModel
    lateinit var adapter: GithubUsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initBinding()

        adapter = GithubUsersAdapter(mutableListOf())
        recycler_view.adapter = adapter
    }

    private fun initViewModel() {
        vm = ViewModelProviders
            .of(this)
            .get(GithubUsersViewModel::class.java)

        vm.alertSource.observe(this, Observer { showAlertDialog(it) })
        vm.githubUsersSource.observe(this, Observer {
            adapter.apply {
                githubUsers.clear()
                githubUsers.addAll(0, it)
                notifyDataSetChanged()
            }
        })
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
