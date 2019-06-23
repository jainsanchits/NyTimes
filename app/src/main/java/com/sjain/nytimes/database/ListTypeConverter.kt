package com.sjain.nytimes.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sjain.nytimes.model.Multimedia
import java.util.*

class ListTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun stringToList(data: String): ArrayList<Multimedia>? {
        val listType = object : TypeToken<ArrayList<Multimedia>>() {

        }.type

        return gson.fromJson<ArrayList<Multimedia>>(data, listType)
    }

    @TypeConverter
    fun ListToString(someObjects: ArrayList<Multimedia>): String {
        return gson.toJson(someObjects)
    }
}
