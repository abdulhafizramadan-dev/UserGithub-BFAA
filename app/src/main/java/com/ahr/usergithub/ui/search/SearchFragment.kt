package com.ahr.usergithub.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.Toolbar
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
import com.ahr.usergithub.databinding.FragmentSearchBinding
import com.ahr.usergithub.ui.UserGithubViewModelFactory
import com.ahr.usergithub.util.LottieViewType
import com.ahr.usergithub.util.shareUser

class SearchFragment : Fragment(), Toolbar.OnMenuItemClickListener,
    UserListAdapter.OnItemClickListener {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var userListAdapter: UserListAdapter
    private lateinit var ime: InputMethodManager

    private val searchViewModel: SearchViewModel by viewModels {
        UserGithubViewModelFactory(
            UserGithubRepository.getInstance(RemoteDataSource.getInstance(GithubConfig.getService()))
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userListAdapter = UserListAdapter(this)
        ime = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        setupToolbar()
        setupTieSearch()
        setupRecyclerView()
        observeListUser()

        if (searchViewModel.firstLoad.value == true) {
            ime.showSoftInput(binding.tieSearch, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun setupToolbar() {
        binding.toolbar.apply {
            setOnMenuItemClickListener(this@SearchFragment)
            setNavigationOnClickListener {
                ime.hideSoftInputFromWindow(view?.windowToken, 0)
                activity?.onBackPressed()
            }
        }
    }

    private fun setupTieSearch() {
        binding.tieSearch.apply {
            requestFocus()
            imeOptions = EditorInfo.IME_ACTION_SEARCH
            setOnEditorActionListener { _, _, _ ->
                searchUser()
                true
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvUser.adapter = userListAdapter
    }

    private fun observeListUser() {
        searchViewModel.listUser.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Response.Success -> {
                    val listUser = response.data
                    toggleLottieView(type = LottieViewType.SearchingNotFound, state = listUser.isEmpty())
                    toggleTextError(listUser.isEmpty(), getString(R.string.searching_not_found))
                    userListAdapter.submitList(listUser)
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

    private fun toggleLottieView(type: LottieViewType = LottieViewType.Searching, state: Boolean = true) {
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

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_search -> {
                searchUser()
                true
            }
            else -> false
        }
    }

    private fun searchUser() {
        binding.tieSearch.text?.toString()?.let { query ->
            if (query.isNotBlank()) {
                searchViewModel.searchUser(BuildConfig.GITHUB_TOKEN, query)
                ime.hideSoftInputFromWindow(view?.windowToken, 0)
            }
        }

    }

    override fun onBtnShareClicked(user: User) {
        shareUser(user)
    }

    override fun onItemClickListener(user: User) {
        val toDetailFragmentDirections = SearchFragmentDirections.actionSearchFragmentToDetailFragment(user)
        findNavController().navigate(toDetailFragmentDirections)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}