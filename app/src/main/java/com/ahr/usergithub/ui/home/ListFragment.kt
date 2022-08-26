package com.ahr.usergithub.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ahr.usergithub.BuildConfig
import com.ahr.usergithub.R
import com.ahr.usergithub.adapter.UserListAdapter
import com.ahr.usergithub.data.Response
import com.ahr.usergithub.data.User
import com.ahr.usergithub.data.UserGithubRepository
import com.ahr.usergithub.data.network.RemoteDataSource
import com.ahr.usergithub.data.network.service.GithubConfig
import com.ahr.usergithub.databinding.FragmentListBinding
import com.ahr.usergithub.ui.UserGithubViewModelFactory


class ListFragment : Fragment(), UserListAdapter.OnItemClickListener {
    private val TAG = "ListFragment"
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var userListAdapter: UserListAdapter

    private val listViewModel: ListViewModel by viewModels {
        UserGithubViewModelFactory(UserGithubRepository(RemoteDataSource(GithubConfig.getService())))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userListAdapter = UserListAdapter(this)

        setupRecyclerView()

        listViewModel.getListUser(BuildConfig.GITHUB_TOKEN)

        listViewModel.listUser.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Response.Success -> {
                    userListAdapter.submitList(response.data)
                }
                is Response.Error -> {
                    Log.d(TAG, "onViewCreated: Error = ${response.error}")
                }
                is Response.Loading -> {
                    Log.d(TAG, "onViewCreated: Loading")
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvUserGithub.adapter = userListAdapter
    }

    override fun onBtnShareClicked(user: User) {
        val sendIntent = Intent()
            .apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, user.url)
                type = "text/plain"
            }
        val shareIntent = Intent.createChooser(sendIntent, getString(R.string.share_user_title))
        startActivity(shareIntent)
    }

    override fun onItemClickListener(user: User) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}