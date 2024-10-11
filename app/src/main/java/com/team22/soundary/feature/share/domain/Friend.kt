package com.team22.soundary.feature.share.domain


data class Friend(
    val id : String,
    val name: String,
    val image : String?,
    var isSelected : Boolean
)