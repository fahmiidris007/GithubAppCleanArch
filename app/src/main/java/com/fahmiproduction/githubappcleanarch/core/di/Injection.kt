package com.fahmiproduction.githubappcleanarch.core.di

import android.content.Context
import com.fahmiproduction.githubappcleanarch.core.data.UserRepository
import com.fahmiproduction.githubappcleanarch.core.data.source.local.LocalDataSource
import com.fahmiproduction.githubappcleanarch.core.data.source.local.room.UserDatabase
import com.fahmiproduction.githubappcleanarch.core.data.source.remote.RemoteDataSource
import com.fahmiproduction.githubappcleanarch.core.data.source.remote.network.ApiConfig
import com.fahmiproduction.githubappcleanarch.core.domain.repository.IUserRepository
import com.fahmiproduction.githubappcleanarch.core.domain.usecase.UserInteractor
import com.fahmiproduction.githubappcleanarch.core.domain.usecase.UserUseCase
import com.fahmiproduction.githubappcleanarch.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IUserRepository {
        val database = UserDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.userDao())
        val appExecutors = AppExecutors()

        return UserRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideUserUseCase(context: Context): UserUseCase {
        val repository = provideRepository(context)
        return UserInteractor(repository)
    }
}
