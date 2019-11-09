package com.future.github.users.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.future.github.BR
import com.future.github.MAX_USERS_LOAD
import com.future.github.R
import com.future.github.users.viewmodel.GithubUsersViewModel
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_git_hub_users.*
import org.jetbrains.anko.toast
import java.util.concurrent.TimeUnit


class GitHubUsersActivity : AppCompatActivity() {

    // region [field]
    private lateinit var vm: GithubUsersViewModel
    private lateinit var adapter: GithubUsersAdapter

    private val toastSubject = PublishSubject.create<String>()
    private val disposableList = mutableListOf<Disposable>()
    // endregion

    // region [activity function]
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        initBinding()
        initAdapter()
        initToastSubscribe()
    }


    override fun onDestroy() {
        super.onDestroy()
        disposeToastSubscribe()
    }
    // endregion

    // region [init function]
    private fun initViewModel() {
        vm = ViewModelProviders
            .of(this)
            .get(GithubUsersViewModel::class.java)
            .apply {
                alertSource.observe(this@GitHubUsersActivity, Observer { showAlertDialog(it) })
                githubUsersSource.observe(this@GitHubUsersActivity, Observer {
                    adapter.apply {
                        githubUsers.addAll(it)
                        notifyDataSetChanged()
                    }
                })
            }
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

    private fun initAdapter() {
        adapter = GithubUsersAdapter(mutableListOf())
        recycler_view.apply {
            adapter = this@GitHubUsersActivity.adapter
            addOnScrollListener(scrollListener)
        }
    }

    private fun initToastSubscribe() {
        toastSubject.throttleFirst(3000, TimeUnit.MILLISECONDS)
            .subscribe { toast(it) }
            .also { disposableList.add(it) }
    }

    private fun disposeToastSubscribe() {
        if (disposableList.size == 0) {
            return
        }
        disposableList.forEach { it.dispose() }
    }
    // endregion

    // region [other]
    private fun showAlertDialog(content: String) {
        AlertDialog.Builder(this).apply {
            setMessage("${getString(R.string.error_msg_start)} $content")
            setTitle(getString(R.string.alert_dialog_title_default))
            setPositiveButton(getString(R.string.btn_positive_text_default), null)
        }.create().show()
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            if (recyclerView.layoutManager !is LinearLayoutManager || !(::adapter.isInitialized)) {
                showAlertDialog(getString(R.string.error_adapter_init_or_not_linear_layout))
                return
            }
            val lastVisibleItemPosition =
                (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

            val lastCount = lastVisibleItemPosition + 1

            if (lastCount >= MAX_USERS_LOAD) {
                toastSubject.onNext(getString(R.string.load_limit))
                return
            }
            if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                lastCount == adapter.itemCount
            ) {
                // load more
                vm.onLoadMore(lastCount)
                toastSubject.onNext(getString(R.string.load_more_users))
            }
        }
    }
    // endregion
}
