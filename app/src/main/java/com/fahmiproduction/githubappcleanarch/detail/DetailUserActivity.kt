package com.fahmiproduction.githubappcleanarch.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.fahmiproduction.githubappcleanarch.R
import com.fahmiproduction.githubappcleanarch.core.data.Resource
import com.fahmiproduction.githubappcleanarch.core.domain.model.User
import com.fahmiproduction.githubappcleanarch.core.ui.ViewModelFactory
import com.fahmiproduction.githubappcleanarch.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private var isFavorite = false
    private lateinit var userData: User


    private lateinit var detailUserViewModel: DetailViewModel
    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        detailUserViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val username = intent.getStringExtra(EXTRA_DATA)
        showDetailUser(username)

    }

    private fun showDetailUser(username: String?) {
        username?.let {
            detailUserViewModel.getDetailUser(username).observe(this) { user ->
//                if (user != null) {
                when (user) {
                    is Resource.Loading -> binding.progressbar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.apply {
                            progressbar.visibility = View.GONE
                            tvName.text = user.data?.name
                            tvUsername.text = user.data?.login
                            tvCompany.text = user.data?.company
                            tvLocation.text = user.data?.location
                            numOfRepo.text = user.data?.publicRepos.toString()
                            tvFollowers.text = user.data?.followers.toString()
                            tvFollowings.text = user.data?.following.toString()
                        }

                        Glide.with(this@DetailUserActivity)
                            .load(user.data?.avatarUrl)
                            .into(binding.ivAvatarImg)


                        userData = user.data!!
                        detailUserViewModel.getFavoriteState(username)
                            ?.observe(this) { userData ->
                                isFavorite = userData.isFavorite == true
                                setStatusFavorite(isFavorite)
                            }
                    }

                    is Resource.Error -> {
                        binding.progressbar.visibility = View.GONE
                        binding.noData.visibility = View.VISIBLE
                    }
                }
                setStatusFavorite(isFavorite)
                binding.fabFavorite.setOnClickListener {
                    if (isFavorite) {
                        userData.isFavorite = !isFavorite
                        detailUserViewModel.deleteFavoriteUser(user.data!!)
                        isFavorite = !isFavorite
                    } else {
                        userData.isFavorite = !isFavorite
                        detailUserViewModel.insertFavoriteUser(user.data!!)
                        isFavorite = !isFavorite
                    }
                    setStatusFavorite(isFavorite)
                }
//                }

            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.baseline_favorite_24
                )
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.baseline_favorite_border_24
                )
            )
        }
    }
}