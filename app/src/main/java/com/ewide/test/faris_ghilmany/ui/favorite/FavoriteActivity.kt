package com.ewide.test.faris_ghilmany.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ewide.test.faris_ghilmany.R
import com.ewide.test.faris_ghilmany.core.data.Resource
import com.ewide.test.faris_ghilmany.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {

    private val binding by lazy { ActivityFavoriteBinding.inflate(layoutInflater) }
    private val viewModel: FavoriteViewModel by viewModel()

    private val adapter = FavoriteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvFavorite.apply {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            adapter = this@FavoriteActivity.adapter
        }
        viewModel.getFavoriteGame.observe(this){
            adapter.setList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteGame()
    }
}