package com.fahmiproduction.githubappcleanarch.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.fahmiproduction.githubappcleanarch.R
import com.fahmiproduction.githubappcleanarch.core.data.source.remote.response.DetailUserResponse
import com.fahmiproduction.githubappcleanarch.core.domain.model.User
import com.fahmiproduction.githubappcleanarch.core.ui.ViewModelFactory
import com.fahmiproduction.githubappcleanarch.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_POSITION = "extra_position"
    }

    private lateinit var detailUserViewModel: DetailViewModel
    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        detailUserViewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]

        val idUser = intent.getStringExtra(EXTRA_DATA)
        showDetailUser(idUser)
    }

    private fun showDetailUser(idUser: String?) {}

}