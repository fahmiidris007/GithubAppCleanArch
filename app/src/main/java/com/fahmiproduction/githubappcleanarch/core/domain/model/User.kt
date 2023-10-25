package com.fahmiproduction.githubappcleanarch.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val login: String,
    val type: String,
    val avatarUrl: String,
    val isFavorite: Boolean
) : Parcelable
