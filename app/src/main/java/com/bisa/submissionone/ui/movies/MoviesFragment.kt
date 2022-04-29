package com.bisa.submissionone.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bisa.submissionone.data.source.local.entity.movies.CatalogMovieEntity
import com.bisa.submissionone.databinding.FragmentMoviesBinding
import com.bisa.submissionone.ui.detail.DetailMovieActivity
import com.bisa.submissionone.utils.ToastCustom
import com.bisa.submissionone.viewmodel.ViewModelFactory
import com.bisa.submissionone.vo.Status


class MoviesFragment : Fragment() {


    private lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){

            showListMovie()

        }

    }

    private fun showListMovie() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        val movieAdapter = MovieAdapter()

        viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                when (movies.status) {

                    Status.LOADING -> {
                        binding.loadingMovie.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        binding.loadingMovie.visibility = View.GONE
                        movieAdapter.submitList(movies.data)
                        binding.rvMovies.layoutManager = GridLayoutManager(requireContext(), 2)

                    }
                    Status.ERROR -> {
                        binding.loadingMovie.visibility = View.GONE
                        ToastCustom.toastIconError(requireActivity(), layoutInflater)
                    }

                }
            }
        })

        with(binding.rvMovies){
            //layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            itemAnimator = null
            adapter = movieAdapter

        }


        movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: CatalogMovieEntity) {
                val intent = Intent(context, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_DATA, movie)
                intent.putExtra(DetailMovieActivity.EXTRA_TYPE, DetailMovieActivity.TYPE_MOVIE)
                startActivity(intent)
            }

        })

    }


}