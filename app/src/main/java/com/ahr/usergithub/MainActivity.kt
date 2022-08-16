package com.ahr.usergithub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ahr.usergithub.adapter.UserListAdapter
import com.ahr.usergithub.data.User
import com.ahr.usergithub.data.UserData
import com.ahr.usergithub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), UserListAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val listUser: List<User> by lazy {
        UserData.getUserData(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val userListAdapter = UserListAdapter(this)
            .apply { submitList(listUser) }
        binding.rvUserGithub.adapter = userListAdapter
    }

    override fun onBtnShareClicked(user: User) {
        val sendIntent = Intent()
            .apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, getString(R.string.user_link, user.username))
                type = "text/plain"
            }
        val shareIntent = Intent.createChooser(sendIntent, getString(R.string.share_user_title))
        startActivity(shareIntent)
    }

    override fun onItemClickListener(user: User) {
        val toDetailUserIntent = Intent(this, DetailUserActivity::class.java)
            .apply { putExtra(DetailUserActivity.EXTRA_USER, user) }
        startActivity(toDetailUserIntent)
    }
}