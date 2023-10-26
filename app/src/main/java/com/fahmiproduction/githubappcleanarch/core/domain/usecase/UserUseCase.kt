package com.fahmiproduction.githubappcleanarch.core.domain.usecase

import androidx.lifecycle.LiveData
import com.fahmiproduction.githubappcleanarch.core.data.Resource
import com.fahmiproduction.githubappcleanarch.core.domain.model.User

interface UserUseCase {
    fun getAllUser(): LiveData<Resource<List<User>>>

    fun getDetailUser(username: String): LiveData<Resource<User>>

    fun getFavoriteUser(): LiveData<List<User>>
    fun setFavoriteUser(user: User, state: Boolean)
}