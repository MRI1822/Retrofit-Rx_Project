package com.mrigankprojects.myretrofit_rx.rx

import com.mrigankprojects.myretrofit_rx.api.response.NewsListResponse

interface RxAPICallback {
    fun onSuccess(newsItems: NewsListResponse)

    fun onFailed(throwable: Throwable)
}