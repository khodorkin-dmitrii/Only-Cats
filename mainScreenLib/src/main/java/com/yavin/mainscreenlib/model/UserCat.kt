package com.yavin.mainscreenlib.model

data class UserCatDto(
    val id: String,
    val name: String,
    val imageUrl: String
)

data class UserCat(
    val id: String,
    val name: String,
    val imageUrl: String
)

fun UserCatDto.toCat(): UserCat {
    return UserCat(
        id = id,
        name = name,
        imageUrl = imageUrl
    )
}