package com.ahr.usergithub.ui.detail

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    val titles = arrayListOf<String>()
    var username = ""

    override fun getItemCount(): Int {
        return DetailFragment.TAB_TITLES.size
    }

    override fun createFragment(position: Int): Fragment {
        return FollowFragment().apply {
            arguments = bundleOf(FollowFragment.EXTRA_FOLLOW to titles[position], FollowFragment.EXTRA_USERNAME to username)
        }
    }
}