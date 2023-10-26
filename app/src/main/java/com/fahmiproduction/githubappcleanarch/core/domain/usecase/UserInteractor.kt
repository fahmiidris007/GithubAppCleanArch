package com.fahmiproduction.githubappcleanarch.core.domain.usecase

import androidx.lifecycle.LiveData
import com.fahmiproduction.githubappcleanarch.core.data.Resource
import com.fahmiproduction.githubappcleanarch.core.data.UserRepository
import com.fahmiproduction.githubappcleanarch.core.domain.model.User
import com.fahmiproduction.githubappcleanarch.core.domain.repository.IUserRepository

class UserInteractor (private val userRepository: IUserRepository): UserUseCase {

    override fun getAllUser() = userRepository.getAllUser()

    override fun getDetailUser(username:String)= userRepository.getDetailUser(username)

    override fun getFavoriteUser() = userRepository.getFavoriteUser()

    override fun setFavoriteUser(user: User, state: Boolean) = userRepository.setFavoriteUser(user, state)
}