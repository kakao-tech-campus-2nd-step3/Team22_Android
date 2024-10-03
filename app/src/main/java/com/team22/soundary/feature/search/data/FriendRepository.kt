package com.team22.soundary.feature.search.data

class FriendRepository {

    // 새로운 친구 목록 반환
    fun getNewFriends(): List<FriendEntity> {
        return listOf(
            FriendEntity("김고고", "@gogoKim", "Ballad", true),
            FriendEntity("박고고", "@parkKim", "Jazz", true)
        )
    }

    // 대기 중인 친구 목록 반환
    fun getPendingFriends(): List<FriendEntity> {
        return listOf(
            FriendEntity("어피치", "@apeach", "K-pop", true),
            FriendEntity("라이언", "@ryan", "Jazz", true),
            FriendEntity("콘", "@cony", "Rock", true)
        )
    }

    // 내 친구 목록 반환
    fun getMyFriends(): List<FriendEntity> {
        return listOf(
            FriendEntity("김남남", "@nyamnyam", "R&B", false),
            FriendEntity("이남남", "@nyamnyam_22", "Jpop", false),
            FriendEntity("쿠키즈용", "@kookooyong", "POP", false),
            FriendEntity("쿠키즈", "@kookoo", "POP", false)
        )
    }
}
