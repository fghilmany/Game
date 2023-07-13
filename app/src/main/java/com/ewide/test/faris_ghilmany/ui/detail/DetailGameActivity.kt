package com.ewide.test.faris_ghilmany.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.ewide.test.faris_ghilmany.core.data.Resource
import com.ewide.test.faris_ghilmany.core.domain.model.DetailGame
import com.ewide.test.faris_ghilmany.databinding.ActivityDetailGameBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class DetailGameActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailGameBinding.inflate(layoutInflater) }
    private val viewModel: DetailGameViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dealsId = intent.getStringExtra(DEALS_ID_EXTRA)
        Timber.e(dealsId.toString())
        viewModel.setGameId(dealsId)
        viewModel.getDetailGame().observe(this){
            when(it){
                is Resource.Loading -> {
                    binding.pbLoading.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.pbLoading.visibility = View.GONE
                    val data = it.data
                    viewModel.setDetailGame(data)
                    setLayout(data)
                }
                is Resource.Error -> {
                    binding.pbLoading.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setLayout(data: DetailGame?) {
        with(binding){
            data?.apply {
                Glide.with(this@DetailGameActivity)
                    .load(thumb.toString())
                    .into(ivThumb)
                tvTitle.text = title
                tvRetailPrice.text = retailPrice
                tvSalesPrice.text = salePrice
                tvRating.text = steamRatingPercent
                tvRatingCount.text = steamRatingCount
                tvRatingText.text = steamRatingText
            }
        }
    }

    companion object{
        const val DEALS_ID_EXTRA = "game_id_extra"
    }
}