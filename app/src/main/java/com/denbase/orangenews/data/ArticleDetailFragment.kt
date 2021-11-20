package com.denbase.orangenews.data

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.denbase.orangenews.R
import com.denbase.orangenews.databinding.FragmentArticleDetailBinding
import com.denbase.orangenews.utils.downloadImage
import com.denbase.orangenews.utils.showView
import com.denbase.orangenews.viewmodels.ArticlesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailFragment : Fragment(R.layout.fragment_article_detail) {

    private lateinit var binding: FragmentArticleDetailBinding
    private lateinit var viewModel: ArticlesViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleDetailBinding.bind(view)

        viewModel = ViewModelProvider(requireActivity()).get(ArticlesViewModel::class.java)




        val detailTitle =  arguments?.getString("detailTitle")
        val detailDesc =  arguments?.getString("detailDescription")
        val detailImg =  arguments?.getString("detailImageUrl")
        val detailUrl = arguments?.getString("detailUrl")

        binding.apply {
            txtArticleDetailTitle.text = detailTitle
            txtArticleDetailDescription.text = detailDesc
            imgArticleDetailImage.downloadImage(detailImg)
            btnDetail.setOnClickListener {
                wvArticleDetail.showView()
                if (detailUrl != null) {
                    wvArticleDetail.loadUrl(detailUrl)
                }
            }

        }




    }

}