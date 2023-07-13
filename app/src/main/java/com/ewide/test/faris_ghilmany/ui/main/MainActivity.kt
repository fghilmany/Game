package com.ewide.test.faris_ghilmany.ui.main

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.ewide.test.faris_ghilmany.databinding.ActivityMainBinding
import com.ewide.test.faris_ghilmany.ui.favorite.FavoriteActivity
import com.ewide.test.faris_ghilmany.ui.paging.LoadingStateAdapter
import com.ewide.test.faris_ghilmany.utils.SharedPreferenceManager
import com.ewide.test.faris_ghilmany.utils.toInt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope{

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModel()
    private lateinit var preferenceManager: SharedPreferenceManager

    private val adapter = GameAdapter()
    private var sortItem: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        preferenceManager = SharedPreferenceManager(this).instance
        sortItem = preferenceManager.getBoolean(SharedPreferenceManager.IS_DESCENDING_KEY)

        setUI()

        viewModel.getGame()
        viewModel.getGame.observe(this){
            adapter.submitData(lifecycle, it)
        }

    }

    private fun setUI() {
        with(binding){
            rvGame.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter =
                    this@MainActivity.adapter.withLoadStateFooter(
                        footer = LoadingStateAdapter{
                            this@MainActivity.adapter.retry()
                        }
                    )
            }
            if (sortItem == true) ibSort.scaleY = 1f else ibSort.scaleY -1f
            etSearch.addTextChangedListener(object : TextWatcher{
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
            fabFavorite.setOnClickListener {
                Intent(this@MainActivity, FavoriteActivity::class.java).apply {
                    startActivity(this)
                }
            }

            ibSort.setOnClickListener {
                preferenceManager.setValue(SharedPreferenceManager.IS_DESCENDING_KEY,
                    sortItem != true
                )
                sortItem = SharedPreferenceManager(this@MainActivity).getBoolean(SharedPreferenceManager.IS_DESCENDING_KEY)
                val message: String
                if (sortItem == true) {
                    ibSort.scaleY = 1f
                    message = "Sort Ascending"
                } else {
                    ibSort.scaleY = -1f
                    message = "Sort Descending"
                }
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                viewModel.setSort(sortItem?.toInt().toString())
                adapter.submitData(lifecycle, PagingData.empty())
                viewModel.getGame()
            }
        }
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main
}