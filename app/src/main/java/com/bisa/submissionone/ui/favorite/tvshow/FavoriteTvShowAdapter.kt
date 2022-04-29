package com.bisa.submissionone.ui.favorite.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bisa.submissionone.R
import com.bisa.submissionone.data.source.remote.api.ConfigNetwork
import com.bisa.submissionone.data.source.local.entity.tvshow.CatalogTvEntity
import com.bisa.submissionone.databinding.ItemTvShowBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class FavoriteTvShowAdapter: PagedListAdapter<CatalogTvEntity, FavoriteTvShowAdapter.FavoriteTvShowViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CatalogTvEntity>(){
            override fun areItemsTheSame(
                oldItem: CatalogTvEntity,
                newItem: CatalogTvEntity
            ): Boolean {
                return oldItem.dataId == newItem.dataId
            }

            override fun areContentsTheSame(
                oldItem: CatalogTvEntity,
                newItem: CatalogTvEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    inner class FavoriteTvShowViewHolder(private val binding: ItemTvShowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: CatalogTvEntity) {
            with(binding){
                Glide.with(itemView.context)
                    .asBitmap()
                    .load(ConfigNetwork.IMAGE_URL + tvShow.imagePoster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error))
                    .into(imgPoster)

                tvItemTvShowTitle.text = tvShow.title

                itemView.setOnClickListener{
                    onItemClickCallback?.onItemClicked(tvShow)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTvShowViewHolder {
        val itemBinding = ItemTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteTvShowViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: FavoriteTvShowViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null){
            holder.bind(tvShow)
        }
    }



    interface OnItemClickCallback {
        fun onItemClicked(movie: CatalogTvEntity)
    }


}