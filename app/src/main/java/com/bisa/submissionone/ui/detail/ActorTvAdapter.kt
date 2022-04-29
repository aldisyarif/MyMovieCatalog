package com.bisa.submissionone.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bisa.submissionone.R
import com.bisa.submissionone.data.source.remote.api.ConfigNetwork
import com.bisa.submissionone.data.source.local.entity.tvshow.ActorTvEntity
import com.bisa.submissionone.databinding.ItemActorBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class ActorTvAdapter: RecyclerView.Adapter<ActorTvAdapter.ActorViewHolder>() {

    private val listActor = ArrayList<ActorTvEntity>()

    fun setActors(actors: List<ActorTvEntity>) {

        this.listActor.clear()
        this.listActor.addAll(actors)
    }

    inner class ActorViewHolder(private val binding: ItemActorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(actor: ActorTvEntity) {
            with(binding) {
                Glide.with(itemView.context)
                        .load(ConfigNetwork.IMAGE_URL + actor.imgPath)
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_error))
                        .into(imgCircularActor)

                tvNameActor.text = actor.name
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        val itemBinding = ItemActorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ActorViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actor = listActor[position]
        holder.bind(actor)
    }

    override fun getItemCount(): Int = listActor.size

}