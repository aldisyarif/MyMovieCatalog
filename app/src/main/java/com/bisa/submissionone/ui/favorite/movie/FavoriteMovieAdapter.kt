package com.bisa.submissionone.ui.favorite.movie

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bisa.submissionone.R
import com.bisa.submissionone.data.source.remote.api.ConfigNetwork
import com.bisa.submissionone.data.source.local.entity.movies.CatalogMovieEntity
import com.bisa.submissionone.databinding.ItemMovieBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class FavoriteMovieAdapter: PagedListAdapter<CatalogMovieEntity, FavoriteMovieAdapter.FavoriteMovieViewHolder>(DIFF_CALLBACK) {


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CatalogMovieEntity>(){
            override fun areItemsTheSame(
                oldItem: CatalogMovieEntity,
                newItem: CatalogMovieEntity
            ): Boolean {
                return oldItem.dataId == newItem.dataId
            }

            override fun areContentsTheSame(
                oldItem: CatalogMovieEntity,
                newItem: CatalogMovieEntity
            ): Boolean {
                return oldItem == newItem
            }


        }
    }



    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    inner class FavoriteMovieViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: CatalogMovieEntity) {
            with(binding){

                Glide.with(itemView.context)
                    .asBitmap()
                    .load(ConfigNetwork.IMAGE_URL + movie.imagePoster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgPoster)

                tvItemMovieTitle.text = movie.title

                itemView.setOnClickListener{
                    onItemClickCallback?.onItemClicked(movie)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteMovieViewHolder {
        val itemBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteMovieViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FavoriteMovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            Log.d("CEKK", movie.title)
            holder.bind(movie)
        }
    }


    interface OnItemClickCallback {
        fun onItemClicked(movie: CatalogMovieEntity)
    }
}