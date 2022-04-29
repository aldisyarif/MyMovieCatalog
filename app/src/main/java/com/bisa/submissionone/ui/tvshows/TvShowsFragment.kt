package com.bisa.submissionone.ui.tvshows

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.bisa.submissionone.data.source.local.entity.tvshow.CatalogTvEntity
import com.bisa.submissionone.databinding.FragmentTvShowsBinding
import com.bisa.submissionone.ui.detail.DetailMovieActivity
import com.bisa.submissionone.utils.ToastCustom
import com.bisa.submissionone.viewmodel.ViewModelFactory
import com.bisa.submissionone.vo.Status


class TvShowsFragment : Fragment() {

    private lateinit var binding: FragmentTvShowsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            showListTvShow()
        }
    }

    private fun showListTvShow() {

        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

        val tvShowAdapter = TvShowAdapter()
        viewModel.getTvShows().observe(viewLifecycleOwner, { tvShows ->
            if (tvShows != null){
                when(tvShows.status){
                    Status.LOADING -> {
                        binding.loadingTvShow.visibility = View.VISIBLE
                        Toast.makeText(context, "Tunggu s ", Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        binding.loadingTvShow.visibility = View.GONE
                        tvShowAdapter.submitList(tvShows.data!!)
                        binding.rvTvShow.layoutManager = GridLayoutManager(context, 2)
                    }
                    Status.ERROR -> {
                        binding.loadingTvShow.visibility = View.GONE
                        ToastCustom.toastIconError(requireActivity(), layoutInflater)
                    }
                }
            }
        })

        with(binding.rvTvShow){
            //layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }

        tvShowAdapter.setOnItemClickCallback(object : TvShowAdapter.OnItemClickCallback{
            override fun onItemClicked(movie: CatalogTvEntity) {
                val intent = Intent(context, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.EXTRA_DATA, movie)
                intent.putExtra(DetailMovieActivity.EXTRA_TYPE, DetailMovieActivity.TYPE_TV_SHOW)
                startActivity(intent)
            }

        })

    }


}