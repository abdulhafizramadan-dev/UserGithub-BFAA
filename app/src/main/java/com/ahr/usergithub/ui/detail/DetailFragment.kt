package com.ahr.usergithub.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ahr.usergithub.R
import com.ahr.usergithub.data.User
import com.ahr.usergithub.databinding.FragmentDetailBinding
import com.ahr.usergithub.util.shareUser
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator

class DetailFragment : Fragment(), Toolbar.OnMenuItemClickListener {

    companion object {
        @StringRes
        val TAB_TITLES = intArrayOf(R.string.followers, R.string.following)
    }

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val navArgs: DetailFragmentArgs by navArgs()

    private lateinit var sectionPagerAdapter: SectionPagerAdapter

    private val user: User by lazy {
        navArgs.user
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupDetailScreen()
        setupViewPager()
        setupTabLayout()
    }

    private fun setupToolbar() {
        binding.toolbar.apply {
            setOnMenuItemClickListener(this@DetailFragment)
            setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun setupDetailScreen() {
        binding.tvUserName.text = user.name
        binding.tvUserCompany.text = user.company
        binding.tvUserUsername.text = user.login
        binding.tvUserLocation.text = user.location
        binding.tvUserRepositories.text = getString(R.string.format_repositories, user.publicRepos)
        binding.tvUserFollowers.text = getString(R.string.format_followers, user.followers)
        binding.tvUserFollowing.text = getString(R.string.format_following, user.following)

        Glide.with(this)
            .load(user.avatarUrl)
            .into(binding.ivUser)
    }

    private fun setupViewPager() {
        sectionPagerAdapter = SectionPagerAdapter(childFragmentManager, lifecycle).apply {
            username = navArgs.user.login
            titles.add(getString(R.string.followers).lowercase())
            titles.add(getString(R.string.following).lowercase())
        }
        binding.viewPager.adapter = sectionPagerAdapter
    }

    private fun setupTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.setText(TAB_TITLES[position])
        }.attach()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_share -> {
                shareUser(user)
                true
            }
            else -> false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}