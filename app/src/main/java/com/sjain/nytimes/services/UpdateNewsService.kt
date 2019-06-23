package com.sjain.nytimes.services

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.sjain.nytimes.networkpkg.APIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UpdateNewsService(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {


    override fun doWork(): Result {

        val mApiService = APIService.create()
        val mCompositeDisposable = CompositeDisposable()
        mCompositeDisposable?.add(
            mApiService.getNewsListing("technology")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ results ->
                    val results1 = results

                }, { error ->
                    error
                }))
        return Result.success();

    }

}
