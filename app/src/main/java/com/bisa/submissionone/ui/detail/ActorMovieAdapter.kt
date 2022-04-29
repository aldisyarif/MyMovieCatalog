package com.bisa.submissionone.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bisa.submissionone.R
import com.bisa.submissionone.data.source.remote.api.ConfigNetwork.IMAGE_URL
import com.bisa.submissionone.data.source.local.entity.movies.ActorMovieEntity
import com.bisa.submissionone.databinding.ItemActorBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ActorMovieAdapter: RecyclerView.Adapter<ActorMovieAdapter.ActorViewHolder>() {

    private val listActor = ArrayList<ActorMovieEntity>()

    fun setActors(actors: List<ActorMovieEntity>){

        this.listActor.clear()
        this.listActor.addAll(actors)
    }

    inner class ActorViewHolder(private val binding: ItemActorBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(actor: ActorMovieEntity) {
            with(binding){
                Glide.with(itemView.context)
                        .load(IMAGE_URL + actor.imgPath)
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