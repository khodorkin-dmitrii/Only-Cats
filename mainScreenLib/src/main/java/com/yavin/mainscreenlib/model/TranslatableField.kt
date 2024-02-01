package com.yavin.mainscreenlib.model

data class TranslatableField(
    val id: Int,
    val title: String? = null,
    val subtitle: String? = null,
    var stateBadge: String? = null
)