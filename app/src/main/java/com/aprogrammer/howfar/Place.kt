package com.aprogrammer.howfar

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(val Name: String?, val distance: Int) : Parcelable