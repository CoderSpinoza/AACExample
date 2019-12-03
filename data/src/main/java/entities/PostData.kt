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
@Entity(tableName = "posts")
data class PostData(val userId: Int, @PrimaryKey val id: Int, val title: String, val body: String) :
    Parcelable

@Serializable
@Parcelize
data class PatchPostData(val title: String? = null, val body: String? = null) : Parcelable