package com.future.github.users.repository

import com.future.github.users.model.GithubUser
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("/users")
    fun users(@Query("since") since: Int = 0): Observable<List<GithubUser>>
}