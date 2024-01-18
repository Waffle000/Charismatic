package com.waffle.charismatic.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Detail(
    val id: Int,
    val name: String?
) : Parcelable