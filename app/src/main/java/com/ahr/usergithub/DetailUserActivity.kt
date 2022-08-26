package com.ahr.usergithub

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.ahr.usergithub.data.User
import com.ahr.usergithub.databinding.ActivityDetailUserBinding
import com.bumptech.glide.Glide

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    private lateinit var binding: ActivityDetailUserBinding
    private val user: User? by lazy {
        intent.getParcelableExtra(EXTRA_USER)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupDetailScreen()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail_user, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
            R.id.action_share -> shareUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun setupDetailScreen() {
        Glide.with(this)
            .load(user?.avatarUrl)
            .into(binding.ivUser)
        binding.tvUserName.text = user?.name
        binding.tvUserUsername.text = getString(R.string.format_username, user?.login)
        binding.tvUserLocation.text = user?.location
        binding.tvUserCompany.text = user?.company
        binding.tvUserRepositories.text = getString(R.string.format_repositories, user?.publicRepos)
        binding.tvUserFollowers.text = getString(R.string.format_followers, user?.followers)
        binding.tvUserFollowing.text = getString(R.string.format_following, user?.following)
    }

    private fun shareUser() {
        val sendIntent = Intent()
            .apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, user?.url)
                type = "text/plain"
            }
        val shareIntent = Intent.createChooser(sendIntent, getString(R.string.share_user_title))
        startActivity(shareIntent)
    }
}