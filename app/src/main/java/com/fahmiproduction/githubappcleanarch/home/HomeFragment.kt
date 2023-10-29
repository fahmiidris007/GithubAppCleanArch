package com.fahmiproduction.githubappcleanarch.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahmiproduction.githubappcleanarch.core.data.Resource
import com.fahmiproduction.githubappcleanarch.core.ui.ListUserAdapter
import com.fahmiproduction.githubappcleanarch.core.ui.ViewModelFactory
import com.fahmiproduction.githubappcleanarch.databinding.FragmentHomeBinding
import com.fahmiproduction.githubappcleanarch.detail.DetailUserActivity


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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
            homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

            homeViewModel.user.observe(viewLifecycleOwner) { user ->
                if (user != null) {
                    when (user) {
                        is Resource.Loading -> binding.progressbar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.rvUser.visibility = View.VISIBLE
                            binding.progressbar.visibility = View.GONE
                            binding.noData.visibility = View.GONE
                            userAdapter.setData(user.data)
                        }

                        is Resource.Error -> {
                            binding.progressbar.visibility = View.GONE
                            binding.noData.visibility = View.VISIBLE
                        }
                    }
                }
            }

            with(binding.rvUser) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = userAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
