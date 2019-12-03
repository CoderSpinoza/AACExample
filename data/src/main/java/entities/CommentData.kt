package com.kanghara.riiidproject.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

/**
 * @author kevin.kang. Created on 2019-11-28..
 */
@Serializable
@Parcelize
@Entity(tableName = "comments")
class CommentData(
    val postId: Int,
    @PrimaryKey val id: Int,
    val name: String,
    val email: String,
    val body: String
) : Parcelable