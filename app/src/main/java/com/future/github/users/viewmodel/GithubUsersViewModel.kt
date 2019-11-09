package com.future.github.users.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.future.github.MAX_USERS_LOAD
import com.future.github.users.model.GithubUser
import com.future.github.users.repository.GithubRepository
import hu.akarnokd.rxjava3.android.AndroidInteropSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception


class GithubUsersViewModel : ViewModel() {

    private val repository = GithubRepository()
    val githubUsersSource = MutableLiveData<List<GithubUser>>().apply { value = listOf() }
    val alertSource = MutableLiveData<String>()

    init {
        githubUsersSource()
            .map { it.subList(0, 20) }
            .subscribe(
                { githubUsersSource.postValue(it) },
                { alertSource.postValue(it.message) }
            )
        // rx kotlin is not available in rx java 3.0 :( so sad.
    }

    private fun githubUsersSource(since: Int = 0): Observable<List<GithubUser>> {
        return repository.users(since)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidInteropSchedulers.mainThread())
    }

    fun onLoadMore(since: Int) {
        githubUsersSource()
            .map {
                if (it.size > MAX_USERS_LOAD) {
                    return@map it.subList(0, 100)
                }
                it
            }
            .subscribe(
                { githubUsersSource.postValue(it) },
                { alertSource.postValue(it.message) }
            )
    }
}