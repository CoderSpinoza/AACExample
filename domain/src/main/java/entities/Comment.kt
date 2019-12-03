package com.kanghara.riiidproject.domain.entities

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

    fun nameAndEmail(): String = "$name (${email})"

    companion object
}