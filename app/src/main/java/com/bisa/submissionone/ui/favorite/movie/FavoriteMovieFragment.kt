package com.bisa.submissionone.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bisa.submissionone.data.source.local.entity.movies.CatalogMovieEntity
import com.bisa.submissionone.databinding.ContentEmptyDataBinding
import com.bisa.submissionone.databinding.ContentFavoriteMoviesBinding
import com.bisa.submissionone.databinding.FragmentFavoriteMovieBinding
import com.bisa.submissionone.ui.detail.DetailMovieActivity
import com.bisa.submissionone.viewmodel.ViewModelFactory

class FavoriteMovieFragment : Fragment() {

    private lateinit var bindingFragment : FragmentFavoriteMovieBinding
    private lateinit var bindingContentFavorite : ContentFavoriteMoviesBinding
    private lateinit var bindingEmpty : ContentEmptyDataBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment

        bindingFragment = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        bindingContentFavorite = bindingFragment.favoriteMoviesContent
        bindingEmpty = bindingFragment.emptyContent

        return bindingFragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){

            showListMovie()

        }
    }

    private fun showListMovie() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]

        bindingFragment.loadingFavoriteMovie.visibility = View.VISIBLE
        bindingFragment.lytFavoriteMovies.visibility = View.GONE
        bindingFragment.lytEmptyMovies.visibility = View.GONE

        val movieAdapter = FavoriteMovieAdapter()

        viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
            if (movies != null) {

                if (movies.isEmpty()){
                    bindingFragment.loadingFavoriteMovie.visibility = View.GONE
                    bindingFragment.lytFavoriteMovies.visibility = View.GONE
                    bindingFragment.lytEmptyMovies.visibility = View.VISIBLE

                }else{
                    bindingFragment.loadingFavoriteMovie.visibility = View.GONE
                    bindingFragment.lytFavoriteMovies.visibility = View.VISIBLE
                    bindingFragment.lytEmptyMovies.visibility = View.GONE


                    movieAdapter.submitList(movies)
                    movieAdapter.notifyDataSetChanged()
                    bindingContentFavorite.rvFavoriteMovies.layoutManager = GridLayoutManager(context, 2)

                }

            }

        })
        with(bindingContentFavorite.rvFavoriteMovies){
            setHasFixedSize(true)
            adapter = movieAdapter
        }
        movieAdapter.setOnItemClickCallback(object : FavoriteMovieAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: CatalogMovieEntity) {
                val intent = Intent(context, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_DATA, movie)
                intent.putExtra(DetailMovieActivity.EXTRA_TYPE, DetailMovieActivity.TYPE_MOVIE)
                startActivity(intent)
            }

        })
    }


}