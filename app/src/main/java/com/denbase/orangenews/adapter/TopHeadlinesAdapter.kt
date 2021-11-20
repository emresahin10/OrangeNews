package com.denbase.orangenews.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.denbase.orangenews.R
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
          holder.apply {
              articleTitle.text = it.title
              articleDescription.text = it.description
              articleImage.downloadImage(it.urlToImage)
          }
      }

        holder.itemView.setOnClickListener {
            val detailTitle = articleList.get(position).title
            val detailDescription = articleList.get(position).description
            val detailImageUrl = articleList.get(position).urlToImage
            val detailUrl = articleList.get(position).url

            it.findNavController().navigate(
                R.id.action_menuItemHome_to_articleDetailFragment,
                bundleOf(
                    "detailTitle" to detailTitle,
                    "detailDescription" to detailDescription,
                    "detailImageUrl" to detailImageUrl,
                    "detailUrl" to detailUrl
                )
            )
        }

    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    private val diffUtil = object: DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

}