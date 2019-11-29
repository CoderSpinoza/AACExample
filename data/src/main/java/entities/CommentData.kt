package com.kanghara.riiidproject.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

/**
 * @author kevin.kang. Created on 2019-11-28..
 */
@Serializable
@Parcelize
class CommentData(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
) : Parcelable