package com.fahmiproduction.githubappcleanarch.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fahmiproduction.githubappcleanarch.core.data.source.remote.network.ApiResponse
import com.fahmiproduction.githubappcleanarch.core.data.source.remote.network.ApiService
import com.fahmiproduction.githubappcleanarch.core.data.source.remote.response.UserDetailResponse
import com.fahmiproduction.githubappcleanarch.core.data.source.remote.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiService): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    fun getAllUser(): LiveData<ApiResponse<List<UserResponse>>> {
        val resultData = MutableLiveData<ApiResponse<List<UserResponse>>>()

        //get data from remote api
        val client = apiService.getList("1")

        client.enqueue(object : Callback<List<UserResponse>> {
            override fun onResponse(
                call: Call<List<UserResponse>>,
                response: Response<List<UserResponse>>
            ) {
                val dataArray = response.body()
                resultData.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultData
    }

    fun getDetailUser(username:String): LiveData<ApiResponse<UserDetailResponse>> {
        val resultData = MutableLiveData<ApiResponse<UserDetailResponse>>()

        //get data from remote api
        val client = apiService.getDetail(username)

        client.enqueue(object : Callback<UserDetailResponse> {
            override fun onResponse(call: Call<UserDetailResponse>, response: Response<UserDetailResponse>) {
                val dataArray = response.body()
                resultData.value = if (dataArray != null) ApiResponse.Success(dataArray) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.e("RemoteDataSource", t.message.toString())
            }
        })

        return resultData
    }

}
