package com.team22.soundary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.team22.soundary.databinding.ActivityMainBinding
import com.team22.soundary.feature.main.presentation.MainFragment
import com.team22.soundary.feature.profile.fragment.ProfileFragment
import com.team22.soundary.feature.search.FriendSearchFragment
import com.team22.soundary.feature.share.ShareMusicFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()

        if (savedInstanceState == null) {
            replaceFragment(MainFragment())
        }
    }

    private fun setupNavigation() {
        binding.nav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.action_main -> {
                    replaceFragment(MainFragment())
                    true
                }
                R.id.action_share -> {
                    replaceFragment(ShareMusicFragment())
                    true
                }
                R.id.action_friend -> {
                    replaceFragment(FriendSearchFragment())
                    true
                }
                R.id.action_my -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, fragment)
            .commitAllowingStateLoss()
    }
}