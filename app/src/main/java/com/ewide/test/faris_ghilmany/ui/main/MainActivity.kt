package com.ewide.test.faris_ghilmany.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ewide.test.faris_ghilmany.databinding.ActivityMainBinding
import com.ewide.test.faris_ghilmany.ui.paging.LoadingStateAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModel()

    private val adapter = GameAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvGame.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter =
                this@MainActivity.adapter.withLoadStateFooter(
                    footer = LoadingStateAdapter{
                        this@MainActivity.adapter.retry()
                    }
                )
        }
        viewModel.getGame.observe(this){
            adapter.submitData(lifecycle, it)
        }

    }
}