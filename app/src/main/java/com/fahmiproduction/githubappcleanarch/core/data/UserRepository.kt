package com.fahmiproduction.githubappcleanarch.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.fahmiproduction.githubappcleanarch.core.data.source.local.LocalDataSource
import com.fahmiproduction.githubappcleanarch.core.data.source.remote.RemoteDataSource
import com.fahmiproduction.githubappcleanarch.core.data.source.remote.network.ApiResponse
import com.fahmiproduction.githubappcleanarch.core.data.source.remote.response.UserResponse
import com.fahmiproduction.githubappcleanarch.core.domain.model.User
import com.fahmiproduction.githubappcleanarch.core.domain.repository.IUserRepository
import com.fahmiproduction.githubappcleanarch.core.utils.AppExecutors
import com.fahmiproduction.githubappcleanarch.core.utils.DataMapper

class UserRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IUserRepository {

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllUser(): LiveData<Resource<List<User>>> =
        object : NetworkBoundResource<List<User>, List<UserResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<User>> {
                return localDataSource.getAllUser().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<User>?): Boolean =
//                data == null || data.isEmpty()
                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override fun createCall(): LiveData<ApiResponse<List<UserResponse>>> =
                remoteDataSource.getAllUser()

            override fun saveCallResult(data: List<UserResponse>) {
                val UserList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertUser(UserList)
            }
        }.asLiveData()

    override fun getFavoriteUser(): LiveData<List<User>> {
        return localDataSource.getFavoriteUser().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteUser(user: User, state: Boolean) {
        val userEntity = DataMapper.mapDomainToEntity(user)
        appExecutors.diskIO().execute { localDataSource.setFavoriteUser(userEntity, state) }
    }
}

