package com.sjain.nytimes.networkpkg

import com.sjain.nytimes.BuildConfig
import com.sjain.nytimes.model.NewsData
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {

    companion object Factory {
        fun create(): APIService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build()

            return retrofit.create(APIService::class.java);
        }
    }

    @GET("svc/topstories/v2/{section}.json?api-key=" + BuildConfig.API_KEY)
    fun getNewsListing(@Path("section") section: String): Observable<NewsData>

}