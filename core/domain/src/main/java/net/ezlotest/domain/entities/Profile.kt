package net.ezlotest.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(val name: String, val avatarUrl: String) : Parcelable