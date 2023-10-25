package com.fahmiproduction.githubappcleanarch.home

import androidx.lifecycle.ViewModel
import com.fahmiproduction.githubappcleanarch.core.domain.usecase.UserUseCase

class HomeViewModel (userUseCase: UserUseCase) : ViewModel() {
    val user = userUseCase.getAllUser()
}
