package com.ewide.test.faris_ghilmany.ui.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ewide.test.faris_ghilmany.core.domain.model.DetailGame
import com.ewide.test.faris_ghilmany.databinding.ItemGameBinding
import com.ewide.test.faris_ghilmany.ui.detail.DetailGameActivity
import timber.log.Timber

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private val listGame = arrayListOf<DetailGame>()
    fun setList(list: List<DetailGame>){
        listGame.clear()
        listGame.addAll(list)
        notifyItemRangeInserted(0, listGame.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return listGame.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = listGame[position]
        holder.bind(result)
    }


    class ViewHolder(binding: View): RecyclerView.ViewHolder(binding) {
        private val binding = ItemGameBinding.bind(binding)
        fun bind(result: DetailGame) {
            with(binding) {
                result.apply {
                    tvTitle.text = name
                    tvPrice.text = salePrice
                    tvRating.text = steamRatingCount
                    Glide.with(binding.root)
                        .load(thumb)
                        .into(ivThumb)

                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context, DetailGameActivity::class.java)
                        intent.putExtra(DetailGameActivity.DEALS_ID_EXTRA, gameID)
                        itemView.context.startActivity(intent)
                    }
                }
            }
        }

    }
}