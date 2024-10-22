package com.team22.soundary.core.domain.model

import android.net.Uri

data class User(
    val id: String = "",
    val name: String = "",
    val email: String = "",
    val image: Uri = Uri.EMPTY,
    val statusMessage: String = "",
    val category: List<Category> = emptyList(),
    var status: String = ""
)
