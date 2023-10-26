package com.fahmiproduction.githubappcleanarch.core.data.source.local

import androidx.lifecycle.LiveData
import com.fahmiproduction.githubappcleanarch.core.data.source.local.entity.UserEntity
import com.fahmiproduction.githubappcleanarch.core.data.source.local.room.UserDao

class LocalDataSource private constructor(private val userDao: UserDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(userDao: UserDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(userDao)
            }
    }

    fun getAllUser(): LiveData<List<UserEntity>> = userDao.getAllUser()

    fun getDetailUser(username: String): LiveData<UserEntity> = userDao.getDetailUser(username)

    fun getFavoriteUser(): LiveData<List<UserEntity>> = userDao.getFavoriteUser()

    fun insertUser(userList: List<UserEntity>) = userDao.insertUser(userList)

    fun insertUser(user: UserEntity) = userDao.insertUser(user)

    fun setFavoriteUser(user: UserEntity, newState: Boolean) {
        user.isFavorite = newState
        userDao.updateFavoriteUser(user)
    }
}