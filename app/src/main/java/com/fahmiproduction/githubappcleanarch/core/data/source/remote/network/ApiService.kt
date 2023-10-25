package com.fahmiproduction.githubappcleanarch.core.data.source.remote.network

import com.fahmiproduction.githubappcleanarch.core.data.source.remote.response.DetailUserResponse
import com.fahmiproduction.githubappcleanarch.core.data.source.remote.response.ListUserResponse
import com.fahmiproduction.githubappcleanarch.core.data.source.remote.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization: token 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")
    @GET("users")
    fun getList(@Query("page") page: String): Call<List<UserResponse>>

    @Headers("Authorization: token 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")
    @GET("users/{username}")
    fun getDetail(@Path("username") username: String): Call<UserResponse>

}