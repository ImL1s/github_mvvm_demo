package com.future.github.users.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
        repository.users()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidInteropSchedulers.mainThread())
            .subscribe { githubUsersSource.postValue(it) }

        Observable.error<Exception>(Exception("111"))
            .doOnError { alertSource.postValue(it.message) }
            .subscribe({}, { alertSource.postValue(it.message) })


//        githubUsersSource.value = listOf(GithubUser(), GithubUser())
//        githubUsersSource.value = listOf(GithubUser(), GithubUser())
//        githubUsersSource.value = listOf(GithubUser(), GithubUser())

//        githubUsersSource.postValue(listOf(GithubUser()))
//        githubUsersSource.postValue(listOf(GithubUser()))
//        githubUsersSource.postValue(listOf(GithubUser()))
    }
}