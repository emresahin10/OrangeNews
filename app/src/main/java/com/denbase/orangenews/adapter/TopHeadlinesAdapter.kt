package com.denbase.orangenews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denbase.orangenews.data.Article
import com.denbase.orangenews.databinding.RvItemTopheadlinesBinding
import com.denbase.orangenews.utils.downloadImage

class TopHeadlinesAdapter(var articleList: List<Article>): RecyclerView.Adapter<TopHeadlinesAdapter.ViewHolder>() {

    class ViewHolder(binding: RvItemTopheadlinesBinding): RecyclerView.ViewHolder(binding.root){

        val articleTitle = binding.txtArticleTitle
        val articleDescription = binding.txtArticleDescription
        val articleImage = binding.imgArticle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(RvItemTopheadlinesBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      articleList.get(position).let {
          holder.articleTitle.text = it.title
          holder.articleDescription.text = it.description
          holder.articleImage.downloadImage(it.urlToImage)
      }
        //TODO("GO DETAIL PAGE , item click")
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

}