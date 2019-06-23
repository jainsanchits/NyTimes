package com.sjain.nytimes.model

import com.google.gson.annotations.SerializedName

data class NewsData(

    @SerializedName("section") var section: String,
    @SerializedName("last_updated") var lastUpdated: String,
    @SerializedName("num_results") var numResults: Int,
    @SerializedName("results") var results: List<NewsItem>
)
