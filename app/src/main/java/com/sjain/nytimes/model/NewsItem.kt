package com.sjain.nytimes.model

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sjain.nytimes.database.ListTypeConverter

@Entity(tableName = "news_item")
data class NewsItem(

    @SerializedName("section")
    @Expose
    @ColumnInfo(name = "section")
    var section: String,
    @SerializedName("subsection")
    @Expose
    @ColumnInfo(name = "subsection")
    var subsection: String,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("url")
    @ColumnInfo(name = "url")
    var url: String,
    @SerializedName("title")
    @Expose
    @ColumnInfo(name = "title")
    var title: String,
    @SerializedName("byline")
    @Expose
    @ColumnInfo(name = "byline")
    var byline: String,
    @SerializedName("multimedia")
    @ColumnInfo(name = "multimedia")
    @TypeConverters(ListTypeConverter::class)
    var multimedia: List<Multimedia>
){
    constructor() : this("","","","", "", emptyList<Multimedia>())
}