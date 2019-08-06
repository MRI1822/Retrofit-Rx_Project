package com.mrigankprojects.myretrofit_rx.api.service

import com.mrigankprojects.myretrofit_rx.api.response.NewsListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("articles")
    fun getNewsApi(@Query("source") source: String,
                   @Query("sortBy") sortby: String,
                   @Query("apiKey") apiKey: String): Observable<NewsListResponse>


}