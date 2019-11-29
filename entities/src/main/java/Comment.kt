package com.kanghara.riiidproject.entities

/**
 * @author kevin.kang. Created on 2019-11-28..
 */
data class Comment(
    val postId: Int,
    val id: Int,
    val name: String,
    val email: String,
    val body: String
) {
    companion object
}