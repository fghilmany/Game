package com.ewide.test.faris_ghilmany.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ewide.test.faris_ghilmany.core.domain.model.Game
import com.ewide.test.faris_ghilmany.databinding.ItemGameBinding
import com.ewide.test.faris_ghilmany.ui.detail.DetailGameActivity
import timber.log.Timber

class GameAdapter: PagingDataAdapter<Game, GameAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = getItem(position)
        holder.bind(result)
    }

    class ViewHolder(private val binding : ItemGameBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Game?) {
            with(binding) {
                result?.apply {
                    tvTitle.text = title
                    tvPrice.text = normalPrice
                    tvRating.text = dealRating
                    Glide.with(binding.root)
                        .load(thumb)
                        .into(ivThumb)

                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context, DetailGameActivity::class.java)
                        intent.putExtra(DetailGameActivity.DEALS_ID_EXTRA, dealsId)
                        itemView.context.startActivity(intent)
                    }
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Game>() {
            override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
                return oldItem.gameId == newItem.gameId
            }
        }
    }
}