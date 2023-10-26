package com.fahmiproduction.githubappcleanarch.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "login")
    var login: String,

    @ColumnInfo(name = "type")
    var type: String? = null,

    @ColumnInfo(name = "avatarUrl")
    var avatarUrl: String? = null,

    @ColumnInfo(name = "company")
    var company: String? = null,

    @ColumnInfo(name = "publicRepos")
    var publicRepos: Int? = null,

    @ColumnInfo(name = "followers")
    var followers: Int? = null,

    @ColumnInfo(name = "following")
    var following: Int? = null,

    @ColumnInfo(name = "name")
    var name: String? = null,

    @ColumnInfo(name = "location")
    var location: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean? = null,
)
