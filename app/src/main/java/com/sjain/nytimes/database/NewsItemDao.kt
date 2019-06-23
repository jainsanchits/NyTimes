package com.sjain.nytimes.database

import androidx.room.*
import com.sjain.nytimes.model.NewsItem
import java.util.*

@Dao
interface NewsItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewsItem(newsItem: NewsItem)

    @Query("DELETE FROM news_item")
    fun deleteAllNewsItem()


    @Query("SELECT * FROM news_item")
    fun getAllnewsItem(): List<NewsItem>
}
