package com.future.github.users.repository

import com.future.github.users.model.GithubUser
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubRepository {

    // TODO dagger2 inject
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
    }

    fun users(since: Int = 0): Observable<List<GithubUser>> {
        return retrofit.create(GithubApi::class.java).users(since)
    }
}