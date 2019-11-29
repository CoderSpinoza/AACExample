package com.kanghara.riiidproject.entities

/**
 * @author kevin.kang. Created on 2019-11-28..
 */
data class Post(val userId: Int, val id: Int, val title: String, val body: String) {
    companion object
}

data class PatchPost(val title: String? = null, val body: String? = null) {
    companion object
}