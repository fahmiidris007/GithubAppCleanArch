package com.fahmiproduction.githubappcleanarch.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahmiproduction.githubappcleanarch.core.ui.ListUserAdapter
import com.fahmiproduction.githubappcleanarch.core.ui.ViewModelFactory
import com.fahmiproduction.githubappcleanarch.databinding.FragmentFavoriteBinding
import com.fahmiproduction.githubappcleanarch.detail.DetailUserActivity

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val userAdapter = ListUserAdapter()
            userAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailUserActivity::class.java).apply {
                    putExtra(DetailUserActivity.EXTRA_DATA, selectedData.login)
                }
                startActivity(intent)
            }

            val factory = ViewModelFactory.getInstance(requireActivity())
            favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            favoriteViewModel.favoriteUser.observe(viewLifecycleOwner) { user ->
                userAdapter.setData(user)
                binding.progressbar.visibility = View.GONE
                binding.noData.visibility = if (user.isNotEmpty()) View.GONE else View.VISIBLE
            }

            with(binding.rvUser) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = userAdapter
            }
        }
    }

}