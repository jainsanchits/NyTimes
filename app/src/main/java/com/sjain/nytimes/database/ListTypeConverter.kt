package com.sjain.nytimes.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sjain.nytimes.model.Multimedia
import java.util.*

class ListTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun stringToList(data: String): List<Multimedia>? {
        val listType = object : TypeToken<List<Multimedia>>() {

        }.type

        return gson.fromJson<List<Multimedia>>(data, listType)
    }

    @TypeConverter
    fun ListToString(someObjects: List<Multimedia>): String {
        return gson.toJson(someObjects)
    }
}
