package com.bisa.submissionone.ui.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bisa.submissionone.data.source.local.entity.tvshow.CatalogTvEntity
import com.bisa.submissionone.databinding.ContentEmptyDataBinding
import com.bisa.submissionone.databinding.ContentFavoriteTvShowsBinding
import com.bisa.submissionone.databinding.FragmentFavoriteTvShowBinding
import com.bisa.submissionone.ui.detail.DetailMovieActivity
import com.bisa.submissionone.viewmodel.ViewModelFactory

class FavoriteTvShowFragment : Fragment() {


    private lateinit var bindingFragment: FragmentFavoriteTvShowBinding
    private lateinit var bindingContentFavorite : ContentFavoriteTvShowsBinding
    private lateinit var bindingEmpty : ContentEmptyDataBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        bindingFragment = FragmentFavoriteTvShowBinding.inflate(inflater, container, false)
        bindingContentFavorite = bindingFragment.favoriteMoviesContent
        bindingEmpty = bindingFragment.emptyContent
        return bindingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            showListTvShow()
        }

    }

    private fun showListTvShow() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]

        bindingFragment.loadingFavoriteTvShow.visibility = View.VISIBLE
        bindingFragment.lytFavoriteTvShow.visibility = View.GONE
        bindingFragment.lytEmptyTvShow.visibility = View.GONE

        val tvShowAdapter = FavoriteTvShowAdapter()
        viewModel.getTvShows().observe(viewLifecycleOwner, { tvShows ->
            if (tvShows != null){

                if (tvShows.isEmpty()){
                    bindingFragment.loadingFavoriteTvShow.visibility = View.GONE
                    bindingFragment.lytFavoriteTvShow.visibility = View.GONE
                    bindingFragment.lytEmptyTvShow.visibility = View.VISIBLE
                }else{
                    bindingFragment.loadingFavoriteTvShow.visibility = View.GONE
                    bindingFragment.lytFavoriteTvShow.visibility = View.VISIBLE
                    bindingFragment.lytEmptyTvShow.visibility = View.GONE

                    tvShowAdapter.submitList(tvShows)
                    bindingContentFavorite.rvTvFavoriteShow.layoutManager = GridLayoutManager(context, 2)
                }


            }
        })
        with(bindingContentFavorite.rvTvFavoriteShow){
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
        tvShowAdapter.setOnItemClickCallback(object : FavoriteTvShowAdapter.OnItemClickCallback{
            override fun onItemClicked(movie: CatalogTvEntity) {
                val intent = Intent(context, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_DATA, movie)
                intent.putExtra(DetailMovieActivity.EXTRA_TYPE, DetailMovieActivity.TYPE_TV_SHOW)
                startActivity(intent)
            }

        })
    }


}