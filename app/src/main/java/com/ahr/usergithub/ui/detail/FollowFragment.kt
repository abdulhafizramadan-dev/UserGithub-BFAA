package com.ahr.usergithub.ui.detail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahr.usergithub.BuildConfig
import com.ahr.usergithub.R
import com.ahr.usergithub.adapter.UserListAdapter
import com.ahr.usergithub.data.Response
import com.ahr.usergithub.data.User
import com.ahr.usergithub.data.UserGithubRepository
import com.ahr.usergithub.data.network.RemoteDataSource
import com.ahr.usergithub.data.network.service.GithubConfig
import com.ahr.usergithub.databinding.FragmentFollowBinding
import com.ahr.usergithub.ui.UserGithubViewModelFactory
import com.ahr.usergithub.util.LottieViewType

class FollowFragment : Fragment(), UserListAdapter.OnItemClickListener {

    companion object {
        const val EXTRA_FOLLOW = "extra_follow"
        const val EXTRA_USERNAME = "extra_username"
    }

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!

    private lateinit var userListAdapter: UserListAdapter

    private val username: String? by lazy {
        arguments?.getString(EXTRA_USERNAME)
    }
    private val follow: String? by lazy {
        arguments?.getString(EXTRA_FOLLOW)
    }

    private val followViewModel: FollowViewModel by viewModels {
        UserGithubViewModelFactory(
            UserGithubRepository.getInstance(RemoteDataSource.getInstance(GithubConfig.getService()))
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userListAdapter = UserListAdapter(this)

        setupRecyclerView()

        if (followViewModel.firstLoad.value == true) {
            if (username != null && follow != null) {
                followViewModel.getListUserFollow(BuildConfig.GITHUB_TOKEN, username!!, follow!!)
            }
        }

        observeListFollow()
    }

    private fun setupRecyclerView() {
        binding.rvUserFollow.adapter = userListAdapter
    }

    private fun observeListFollow() {
        followViewModel.listFollow.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Response.Success -> {
                    toggleLottieView(state = false)
                    toggleTextError(false)
                    userListAdapter.submitList(response.data)
                }
                is Response.Error -> {
                    toggleLottieView(LottieViewType.Error, true)
                    toggleTextError(message = response.error)
                }
                is Response.Loading -> {
                    toggleLottieView()
                    toggleTextError(false)
                }
            }
        }

    }

    private fun toggleLottieView(type: LottieViewType = LottieViewType.Loading, state: Boolean = true) {
        binding.lottieView.visibility = if (state) View.VISIBLE else View.GONE
        binding.lottieView.setAnimation(type.rawRes)
        if (state) {
            binding.lottieView.resumeAnimation()
        } else {
            binding.lottieView.pauseAnimation()
        }
    }

    private fun toggleTextError(state: Boolean = true, message: String? = null) {
        binding.tvTextError.visibility = if (state) View.VISIBLE else View.GONE
        binding.tvTextError.text = message
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
        val toDetailFragmentDirections = DetailFragmentDirections.actionDetailFragmentSelf(user)
        findNavController().navigate(toDetailFragmentDirections)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}