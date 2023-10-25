package com.fahmiproduction.githubappcleanarch.core.domain.usecase

import com.fahmiproduction.githubappcleanarch.core.data.UserRepository
import com.fahmiproduction.githubappcleanarch.core.domain.model.User
import com.fahmiproduction.githubappcleanarch.core.domain.repository.IUserRepository

class UserInteractor (private val userRepository: IUserRepository): UserUseCase {

    override fun getAllUser() = userRepository.getAllUser()

    override fun getFavoriteUser() = userRepository.getFavoriteUser()

    override fun setFavoriteUser(user: User, state: Boolean) = userRepository.setFavoriteUser(user, state)
}