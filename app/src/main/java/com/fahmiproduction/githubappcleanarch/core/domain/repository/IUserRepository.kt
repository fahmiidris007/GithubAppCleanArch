package com.fahmiproduction.githubappcleanarch.core.domain.repository

import androidx.lifecycle.LiveData
import com.fahmiproduction.githubappcleanarch.core.data.Resource
import com.fahmiproduction.githubappcleanarch.core.domain.model.User

interface IUserRepository {

    fun getAllUser(): LiveData<Resource<List<User>>>

    fun getFavoriteUser(): LiveData<List<User>>

    fun setFavoriteUser(user: User, state: Boolean)

}
