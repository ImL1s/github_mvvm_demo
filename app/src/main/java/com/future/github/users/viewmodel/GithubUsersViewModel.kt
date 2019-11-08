package com.future.github.users.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.future.github.users.model.GithubUser
import com.future.github.users.repository.GithubRepository
import hu.akarnokd.rxjava3.android.AndroidInteropSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class GithubUsersViewModel : ViewModel() {

    private val repository = GithubRepository()
    val githubUsers = MutableLiveData<List<GithubUser>>().apply { value = listOf() }

    init {
        repository.users()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidInteropSchedulers.mainThread())
//            .doOnError { print(it.message) }
            .subscribe { githubUsers.postValue(it) }
//        githubUsers.value = listOf(GithubUser(), GithubUser())
//        githubUsers.value = listOf(GithubUser(), GithubUser())
//        githubUsers.value = listOf(GithubUser(), GithubUser())

//        githubUsers.postValue(listOf(GithubUser()))
//        githubUsers.postValue(listOf(GithubUser()))
//        githubUsers.postValue(listOf(GithubUser()))
    }
}