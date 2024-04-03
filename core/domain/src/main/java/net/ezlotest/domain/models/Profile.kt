package net.ezlotest.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Profile(val name: String, val avatarUrl: String) : Parcelable