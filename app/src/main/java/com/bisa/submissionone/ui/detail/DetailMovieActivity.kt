package com.bisa.submissionone.ui.detail

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bisa.submissionone.R
import com.bisa.submissionone.data.source.remote.api.ConfigNetwork.IMAGE_URL
import com.bisa.submissionone.data.*
import com.bisa.submissionone.data.source.local.entity.movies.ActorMovieEntity
import com.bisa.submissionone.data.source.local.entity.movies.CatalogMovieEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.ActorTvEntity
import com.bisa.submissionone.data.source.local.entity.movies.DetailCatalogMovieEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.CatalogTvEntity
import com.bisa.submissionone.data.source.local.entity.tvshow.DetailCatalogTvEntity
import com.bisa.submissionone.databinding.ActivityDetailMovieBinding
import com.bisa.submissionone.databinding.ContentDetailMovieBinding
import com.bisa.submissionone.utils.ToastCustom
import com.bisa.submissionone.viewmodel.ViewModelFactory
import com.bisa.submissionone.vo.Status
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailMovieActivity : AppCompatActivity() {

    companion object {

        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"

        const val TYPE_MOVIE = "type_movie"
        const val TYPE_TV_SHOW = "type_tv_show"
    }

    private lateinit var viewModel: DetailViewModel

    private lateinit var binding: ContentDetailMovieBinding
    private lateinit var activityBinding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        binding = activityBinding.detailContent

        setContentView(activityBinding.root)

        initToolbar()
        initComponent()

    }


    private fun initToolbar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.apply {
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                statusBarColor = resources.getColor(android.R.color.white)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    decorView.systemUiVisibility = (decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
                }
            }
        }

    }

    private fun initComponent() {
        val extras = intent.extras
        if (extras != null){
            val type = extras.getString(EXTRA_TYPE)
            if (type != null){
                val factory = ViewModelFactory.getInstance(this)
                viewModel = ViewModelProvider(this, factory)[DetailViewModel::class.java]
                if (type == TYPE_MOVIE){
                    val data = intent.getParcelableExtra<CatalogMovieEntity>(EXTRA_DATA)
                    if (data != null){
                        viewModel.switchFavoriteCatalogMovie.observe(this, { favoriteStateMovie ->
                            if (favoriteStateMovie != null){
                                val state = favoriteStateMovie.favorite
                                setFavoriteState(state)
                            }
                        })
                        viewModel.setSelectedMovie(data.dataId)
                        viewModel.getContentMovie().observe(this, { movie ->
                            if (movie != null){
                                when(movie.status){
                                    Status.LOADING -> {
                                        activityBinding.loadingDetailCatalog.visibility = View.VISIBLE
                                        activityBinding.lytDetail.visibility = View.GONE
                                    }
                                    Status.SUCCESS -> {
                                        activityBinding.loadingDetailCatalog.visibility = View.GONE
                                        activityBinding.lytDetail.visibility = View.VISIBLE
                                        Log.d("detail == ", movie.data?.title.toString())
                                        populateMovieContent(data, movie.data!!)
                                    }
                                    Status.ERROR -> {
                                        activityBinding.loadingDetailCatalog.visibility = View.GONE
                                        activityBinding.lytDetail.visibility = View.GONE

                                    }
                                }
                            }
                        })
                        viewModel.getActorMovie().observe(this, { actors ->
                            if (actors != null){
                                when(actors.status){
                                    Status.LOADING -> {

                                    }
                                    Status.SUCCESS -> {
                                        showMovieActors(actors.data!!)
                                    }
                                    Status.ERROR -> {

                                    }
                                }
                            }
                        })
                    }
                }else if (type == TYPE_TV_SHOW){
                    val data = intent.getParcelableExtra<CatalogTvEntity>(EXTRA_DATA)
                    if (data != null){
                        viewModel.switchFavoriteCatalogTvShow.observe(this, { favoriteStateTv ->
                            if (favoriteStateTv != null){
                                val state = favoriteStateTv.favorite
                                setFavoriteState(state)
                            }
                        })
                        viewModel.setSelectedTvShow(data.dataId)
                        viewModel.getContentTvShow().observe(this, { tvShow ->
                            if (tvShow != null){
                                when(tvShow.status){
                                    Status.LOADING -> {
                                        activityBinding.loadingDetailCatalog.visibility = View.VISIBLE
                                        activityBinding.lytDetail.visibility = View.GONE
                                    }
                                    Status.SUCCESS -> {
                                        activityBinding.loadingDetailCatalog.visibility = View.GONE
                                        activityBinding.lytDetail.visibility = View.VISIBLE
                                        Log.d("detail == ", tvShow.data?.title.toString())
                                        populateTvContent(data, tvShow.data!!)
                                    }
                                    Status.ERROR -> {
                                        activityBinding.loadingDetailCatalog.visibility = View.GONE
                                        activityBinding.lytDetail.visibility = View.GONE
                                    }
                                }
                            }
                        })
                        viewModel.getActorTvShow().observe(this, { actors ->
                            if (actors != null){
                                when(actors.status){
                                    Status.LOADING -> {

                                    }
                                    Status.SUCCESS -> {
                                        showTvShowActors(actors.data!!)
                                    }
                                    Status.ERROR -> {

                                    }
                                }
                            }
                        })
                    }
                }
            }
        }
    }

    private fun populateMovieContent(movie: CatalogMovieEntity, content: DetailCatalogMovieEntity) {
        Glide.with(this)
                .load(IMAGE_URL + content.imagePoster)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(binding.bgDetailPoster)
        Glide.with(this)
                .load(IMAGE_URL + content.imagePoster)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(binding.imgDetailPoster)
        with(binding){
            tvDetailTitle.text = content.title
            tvDetailTagline.text = content.tagline
            tvDetailRelease.text = content.release_date
            tvDetailDuration.text = content.duration
            tvDetailCategory.text = content.genre
            tvDetailDescription.text = content.description

            percentUserScore.animationDuration = 2200
            percentUserScore.setProgress(content.score!!.toFloat(), true)

            imgShare.setOnClickListener {
                val mimeType = "text/plain"
                ShareCompat.IntentBuilder
                        .from(this@DetailMovieActivity)
                        .setType(mimeType)
                        .setChooserTitle("Bagikan aplikasi ini sekarang")
                        .setText(resources.getString(R.string.share_text, content.title))
                        .startChooser()
            }
            imgFavorite.setOnClickListener {
                viewModel.setFavoriteMovie(movie, movie.favorite)
                setFavoriteState(movie.favorite)
                ToastCustom.toastFloatingImageMovie(this@DetailMovieActivity, layoutInflater, movie, movie.favorite)
            }
        }
    }

    private fun populateTvContent(tvShow: CatalogTvEntity, content: DetailCatalogTvEntity) {
        Glide.with(this)
                .load(IMAGE_URL + content.imagePoster)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(binding.bgDetailPoster)
        Glide.with(this)
                .load(IMAGE_URL + content.imagePoster)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                .into(binding.imgDetailPoster)
        with(binding){
            tvDetailTitle.text = content.title
            tvDetailTagline.text = content.tagline
            tvDetailRelease.text = content.release_date
            tvDetailDuration.text = content.duration
            tvDetailCategory.text = content.genre
            tvDetailDescription.text = content.description

            percentUserScore.animationDuration = 2200
            percentUserScore.setProgress(content.score!!.toFloat(), true)

            imgShare.setOnClickListener {
                val mimeType = "text/plain"
                ShareCompat.IntentBuilder
                        .from(this@DetailMovieActivity)
                        .setType(mimeType)
                        .setChooserTitle("Bagikan aplikasi ini sekarang")
                        .setText(resources.getString(R.string.share_text, content.title))
                        .startChooser()
            }

            imgFavorite.setOnClickListener {
                viewModel.setFavoriteTvShow(tvShow, tvShow.favorite)
                setFavoriteState(tvShow.favorite)
                ToastCustom.toastFloatingImageTvShow(this@DetailMovieActivity, layoutInflater, tvShow, tvShow.favorite)
            }
        }
    }

    private fun showMovieActors(actors: List<ActorMovieEntity>) {
        val actorAdapter = ActorMovieAdapter()
        actorAdapter.setActors(actors)
        actorAdapter.notifyDataSetChanged()
        with(binding.rvDetailActor){
            layoutManager = LinearLayoutManager(this@DetailMovieActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = actorAdapter
        }
    }

    private fun showTvShowActors(actors: List<ActorTvEntity>) {
        val actorAdapter = ActorTvAdapter()
        actorAdapter.setActors(actors)
        actorAdapter.notifyDataSetChanged()
        with(binding.rvDetailActor){
            layoutManager = LinearLayoutManager(this@DetailMovieActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = actorAdapter
        }
    }

    private fun setFavoriteState(state: Boolean) {
        if (state){
            binding.imgFavorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite))
        }else{
            binding.imgFavorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_border))
        }
    }



}