package com.mrigankprojects.myretrofit_rx.rx

import android.support.v7.app.AppCompatActivity
import com.mrigankprojects.myretrofit_rx.activity.MainActivity
import com.mrigankprojects.myretrofit_rx.api.response.NewsListResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RxAPICallHelper {


    fun  call(observable: Observable<NewsListResponse>?, myActivity: AppCompatActivity?): Disposable {
        if (observable == null) {
            throw IllegalArgumentException("Observable must not be null.")
        }

        if (myActivity == null) {
            throw IllegalArgumentException("Callback must not be null.")
        }

        return observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> (myActivity as MainActivity).setNewsData(response) }, { /*throwable ->
                    myActivity.onFailed(throwable)*/
            })


    }
}