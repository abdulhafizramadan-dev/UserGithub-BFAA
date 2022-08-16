package com.ahr.usergithub

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ahr.usergithub.adapter.UserListAdapter
import com.ahr.usergithub.data.User
import com.ahr.usergithub.data.UserData
import com.ahr.usergithub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val listUser: List<User> by lazy {
        UserData.getUserData(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("TAG", "onCreate: $listUser")
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val userListAdapter = UserListAdapter()
            .apply { submitList(listUser) }
        binding.rvUserGithub.adapter = userListAdapter

    }
}