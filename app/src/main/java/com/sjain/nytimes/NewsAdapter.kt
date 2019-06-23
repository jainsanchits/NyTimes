package com.sjain.nytimes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sjain.nytimes.model.NewsItem
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(private val dataList: ArrayList<NewsItem>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)

        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(newsItem: NewsItem) {
            itemView.title.text = newsItem.title
            itemView.author.text = newsItem.byline
            if (newsItem.multimedia != null && newsItem.multimedia.count() > 0) {
                Glide.with(itemView.newsImage.context.applicationContext)
                    .load(newsItem.multimedia.get(0).url1)
                    .into(itemView.newsImage)
            }
        }
    }
}