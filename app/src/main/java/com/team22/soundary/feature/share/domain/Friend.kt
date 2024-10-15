package com.team22.soundary.feature.share.domain


data class Friend(
    val id : String,
    val name: String,
    val image: String?,
    val category: List<Category>,
    var isSelected: Boolean
)

enum class Category {
    RnB, hiphop, pop, rock, jpop, dance
}