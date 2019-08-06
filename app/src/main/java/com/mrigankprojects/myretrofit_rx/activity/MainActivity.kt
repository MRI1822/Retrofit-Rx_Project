package com.mrigankprojects.myretrofit_rx.activity

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.mrigankprojects.myretrofit_rx.R
import com.mrigankprojects.myretrofit_rx.adapter.NewsRecyclerAdapter
import com.mrigankprojects.myretrofit_rx.api.ApiProduction
import com.mrigankprojects.myretrofit_rx.api.response.NewsListResponse
import com.mrigankprojects.myretrofit_rx.api.service.NewsService
import com.mrigankprojects.myretrofit_rx.rx.RxAPICallHelper
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerNews: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        recyclerNews = findViewById(R.id.recyclerNews)
        getNewsList()
    }

    /**
     * Get News List from API
     */
    public fun getNewsList() {
        //Create retrofit Service
        var mNewsService: NewsService = ApiProduction(this).provideService(NewsService::class.java)
        //List of source : https://newsapi.org/sources
        //List of sort by option: https://newsapi.org/#apiArticles
        var apiCall: Observable<NewsListResponse> = mNewsService.getNewsApi("techcrunch", "top",
            getString(R.string.new_api_key) //Test API Key
        )
        RxAPICallHelper().call(apiCall, this)
    }

    public fun setNewsData(newsItems: NewsListResponse) {
        recyclerNews.layoutManager = LinearLayoutManager(this)

        val newsRecyclerAdapter: NewsRecyclerAdapter = NewsRecyclerAdapter()
        newsRecyclerAdapter.setData(newsItems.getArticles() as ArrayList<NewsListResponse.Article>)
        recyclerNews.adapter = newsRecyclerAdapter
        newsRecyclerAdapter.setOnItemClick(object : NewsRecyclerAdapter.MyAdapterListener {
            override fun onItemViewClick(mUrl:String,position: Int) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse(
                            mUrl))
                )
            }
        })
    }

}

