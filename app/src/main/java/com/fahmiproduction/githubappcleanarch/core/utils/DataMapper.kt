package com.fahmiproduction.githubappcleanarch.core.utils

import com.fahmiproduction.githubappcleanarch.core.data.source.local.entity.UserEntity
import com.fahmiproduction.githubappcleanarch.core.data.source.remote.response.UserResponse
import com.fahmiproduction.githubappcleanarch.core.domain.model.User

object DataMapper {
    fun mapResponsesToEntities(input: List<UserResponse>): List<UserEntity> {
        val userList = ArrayList<UserEntity>()
        input.map {
            val user = UserEntity(
                login = it.login,
                type = it.type,
                avatarUrl = it.avatarUrl,
                isFavorite = false
            )
            userList.add(user)
        }
        return userList
    }

    fun mapEntitiesToDomain(input: List<UserEntity>): List<User> =
        input.map {
            User(
                login = it.login,
                type = it.type,
                avatarUrl = it.avatarUrl,
                isFavorite = false
            )
        }

    fun mapDomainToEntity(input: User) = UserEntity(
        login = input.login,
        type = input.type,
        avatarUrl = input.avatarUrl,
        isFavorite = false
    )
}