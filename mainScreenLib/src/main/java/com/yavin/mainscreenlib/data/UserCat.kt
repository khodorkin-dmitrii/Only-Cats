package com.yavin.mainscreenlib.data

data class UserCatDto(
    val id: String,
    val name: String,
    val imageUrl: String,
    val isLiked: Boolean
)

data class UserCat(
    val id: String,
    val name: String,
    val imageUrl: String,
    val isLiked: Boolean
)

fun UserCatDto.toCat(): UserCat {
    return UserCat(
        id = id,
        name = name,
        imageUrl = imageUrl,
        isLiked = isLiked
    )
}