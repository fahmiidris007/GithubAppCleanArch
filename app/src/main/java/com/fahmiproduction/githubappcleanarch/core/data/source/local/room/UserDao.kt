package com.fahmiproduction.githubappcleanarch.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fahmiproduction.githubappcleanarch.core.data.source.local.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUser(): LiveData<List<UserEntity>>

    @Query("SELECT * FROM user where login = :username")
    fun getDetailUser(username: String): LiveData<UserEntity>

    @Query("SELECT * FROM user where isFavorite = 1")
    fun getFavoriteUser(): LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: List<UserEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)

    @Update
    fun updateFavoriteUser(user: UserEntity)
}