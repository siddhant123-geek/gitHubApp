package com.example.githubapp.ui.detail

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import com.bumptech.glide.Glide
import com.example.githubapp.data.models.RepoDetail
import com.example.githubapp.databinding.ActivityRepoDetailBinding

class RepoDetailActivity: AppCompatActivity() {

    private lateinit var binding: ActivityRepoDetailBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRepoDetailBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val selectedRepo = intent.getParcelableExtra("repoDetail", RepoDetail::class.java)
        Log.d(this.javaClass.simpleName, "onCreate: selectedRepo in detail screen ${selectedRepo?.name}")

        if (selectedRepo != null) {
            setupUi(selectedRepo)
        }
    }

    private fun setupUi(selectedRepo: RepoDetail) {
        binding.repoName.text = selectedRepo.name
        binding.repoDescription.text = selectedRepo.description
        binding.repoLanguage.text = selectedRepo.language
        binding.repoProjectLink.text = selectedRepo.projectLink
        Glide.with(binding.repoImage.context)
            .load(selectedRepo.imageUrl)
            .into(binding.repoImage)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        binding.repoProjectLink.setOnClickListener {
            val intent = CustomTabsIntent.Builder().build()
            intent.launchUrl(this, Uri.parse(selectedRepo.projectLink))
        }
    }
}