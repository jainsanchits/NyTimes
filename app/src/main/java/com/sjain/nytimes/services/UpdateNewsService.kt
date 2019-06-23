/*
package com.sjain.nytimes.services

import android.content.Context
import android.widget.Toast
import com.sjain.nytimes.MainActivity
import com.sjain.nytimes.NewsAdapter
import com.sjain.nytimes.model.NewsData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class UpdateNewsService(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {


    override fun doWork(): Result {

            mCompositeDisposable?.add(
                MainActivity.getService().getNewsListing("technology")
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::handleResponse, this::handleError)
            )
        return Result.success();

    }
    private fun handleResponse(newsData: NewsData) {
        UpdateMethodSuccess(newsData);
    }

    private fun handleError(error: Throwable) {
        UpdateMehtodfailure(error);
    }

}*/
