package com.fahmiproduction.githubappcleanarch.detail

import androidx.lifecycle.ViewModel
import com.fahmiproduction.githubappcleanarch.core.domain.model.User
import com.fahmiproduction.githubappcleanarch.core.domain.usecase.UserUseCase

class DetailViewModel(private val userUseCase: UserUseCase) : ViewModel() {

    fun getDetailUser(username: String) =
        userUseCase.getDetailUser(username)

    fun setFavoriteUser(user: User?, newStatus: Boolean?) =
        userUseCase.setFavoriteUser(user!!, newStatus!!)
}
