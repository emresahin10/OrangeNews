package com.denbase.orangenews.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.denbase.orangenews.R
import com.denbase.orangenews.adapter.TopHeadlinesAdapter
import com.denbase.orangenews.databinding.FragmentHomeBinding
import com.denbase.orangenews.utils.Resource
import com.denbase.orangenews.utils.hideView
import com.denbase.orangenews.utils.showView
import com.denbase.orangenews.viewmodels.ArticlesViewModel
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerViewAdapter: TopHeadlinesAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var viewModel: ArticlesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity()).get(ArticlesViewModel::class.java)
        binding.etxtsearchlayout.boxBackgroundMode = TextInputLayout.BOX_BACKGROUND_NONE

        setupAdapter()
        getNews()

    }

    private fun setupAdapter(){
        recyclerViewAdapter = TopHeadlinesAdapter(listOf())
        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.rvTopHeadlines.apply {
            layoutManager = linearLayoutManager
            adapter = recyclerViewAdapter
        }
    }

    private fun getNews(){
        viewModel.getTopHeadlinesNews()
        viewModel.articleList.observe(viewLifecycleOwner){ response ->

            when(response){
                is Resource.Loading -> {
                    binding.pbTopHeadlines.showView()
                }
                is Resource.Success -> {
                    binding.pbTopHeadlines.hideView()
                    response.data?.let {
                        recyclerViewAdapter.articleList = it.articles
                        recyclerViewAdapter.notifyDataSetChanged()
                    }
                }
                is Resource.Error -> {
                    binding.pbTopHeadlines.showView()
                    Log.e("error", response.message.toString())
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getNews()
    }

}