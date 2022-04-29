package com.bisa.submissionone.ui.tvshows

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bisa.submissionone.R
import com.bisa.submissionone.data.source.remote.api.ConfigNetwork.IMAGE_URL
import com.bisa.submissionone.data.source.local.entity.tvshow.CatalogTvEntity
import com.bisa.submissionone.databinding.ItemTvShowBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TvShowAdapter: PagedListAdapter<CatalogTvEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

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


    inner class TvShowViewHolder(private val binding: ItemTvShowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: CatalogTvEntity) {
            with(binding){
                Glide.with(itemView.context)
                        .asBitmap()
                        .load(IMAGE_URL + tvShow.imagePoster)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemBinding = ItemTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        //val tvShow = listTvShow[position]
        val tvShow = getItem(position)
        if (tvShow != null){
            holder.bind(tvShow)
        }

    }

    //override fun getItemCount(): Int = listTvShow.size

    interface OnItemClickCallback {
        fun onItemClicked(movie: CatalogTvEntity)
    }
}