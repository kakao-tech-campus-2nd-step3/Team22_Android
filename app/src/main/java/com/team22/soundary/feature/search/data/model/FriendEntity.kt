package com.team22.soundary.feature.search.data.model

data class FriendEntity(
    val id: String,
    val name: String,
    val email: String,
    val statusMessage: String,
    val favoriteGenres: List<String>,
    var status: String // "pending", "requested", "accepted"
)
