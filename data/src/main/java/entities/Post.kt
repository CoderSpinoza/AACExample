package com.kanghara.riiidproject.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

/**
 * @author kevin.kang. Created on 2019-11-28..
 */
@Serializable
@Parcelize
data class Post(val userId: Int, val id: Int, val title: String, val body: String) : Parcelable

@Serializable
@Parcelize
data class PatchPost(val title: String? = null, val body: String? = null) : Parcelable