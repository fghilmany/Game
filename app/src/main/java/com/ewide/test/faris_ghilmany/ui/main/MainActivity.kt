package com.ewide.test.faris_ghilmany.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.LinearLayoutManager
import com.ewide.test.faris_ghilmany.databinding.ActivityMainBinding
import com.ewide.test.faris_ghilmany.ui.paging.LoadingStateAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope{

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModel()

    private val adapter = GameAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvGame.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter =
                this@MainActivity.adapter.withLoadStateHeaderAndFooter(
                    header = LoadingStateAdapter{
                        this@MainActivity.adapter.retry()
                    },
                    footer = LoadingStateAdapter{
                        this@MainActivity.adapter.retry()
                    }
                )
        }
        viewModel.getGame()
        viewModel.getGame.observe(this){
            adapter.submitData(lifecycle, it)
        }

        binding.etSearch.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                launch {
                    delay(300)
                    viewModel.setSearchQuery(if (s.isNullOrEmpty()) null else s.toString())
                    viewModel.getGame()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main
}