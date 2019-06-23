package com.sjain.nytimes

/*import com.sjain.nytimes.database.NewsDatabase
import com.sjain.nytimes.database.NewsItemDao*/
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sjain.nytimes.database.NewsDatabase
import com.sjain.nytimes.database.NewsItemDao
import com.sjain.nytimes.model.NewsData
import com.sjain.nytimes.model.NewsItem
import com.sjain.nytimes.networkpkg.APIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var mCompositeDisposable: CompositeDisposable? = null

    private var mNewsArrayList: ArrayList<NewsItem>? = null

      private var db: NewsDatabase? = null
      private var newsItemDao: NewsItemDao? = null

    private var mAdapter: NewsAdapter? = null
    var apiService: APIService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mCompositeDisposable = CompositeDisposable()

        db = NewsDatabase.getAppDataBase(this);
        newsItemDao = db?.newsItemDao()
        getService()
        initRecyclerView()

        if (newsItemDao?.getAllnewsItem()?.count()!! > 0) {
            updateView()
        } else {
            loadData()
        }
        //loadData()
    }

    private fun updateView() {
        mAdapter = NewsAdapter(ArrayList(newsItemDao?.getAllnewsItem()!!));
        rv_news_display.adapter = mAdapter
    }


    public fun getService(): APIService {
        if (apiService == null) {
            apiService = APIService.create()
        }
        return apiService as APIService
    }

    private fun initRecyclerView() {

        rv_news_display.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        rv_news_display.layoutManager = layoutManager
    }

    private fun loadData() {

        mCompositeDisposable?.add(
            getService().getNewsListing("technology")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                        results -> handleResponse(results)
                }, {
                        error -> handleError(error)
                })

        )
    }

    private fun handleResponse(androidList: NewsData) {
        if (mAdapter == null) {
            mNewsArrayList = ArrayList(androidList.results)
            mAdapter = NewsAdapter(mNewsArrayList!!)
            rv_news_display.adapter = mAdapter
           /* val myConstraints = Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .build()
            val request = PeriodicWorkRequest
                .Builder(UpdateNewsService::class.java, 10, TimeUnit.MINUTES)
                .setConstraints(myConstraints)
                .build()
            WorkManager.getInstance().enqueue(request)*/

        } else {
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
            mAdapter?.notifyDataSetChanged();
        }
        /*for (newsItem in mNewsArrayList!!) {
            newsItemDao?.insertNewsItem(newsItem)
        }*/
    }

    private fun handleError(error: Throwable) {
        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        mCompositeDisposable?.clear()
    }

    /*class UpdateNewsService(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {


        override fun doWork(): Result {

         return Result.success();

        }
        private fun handleResponse(newsData: NewsData) {
            UpdateMethodSuccess(newsData);
        }

        private fun handleError(error: Throwable) {
            UpdateMehtodfailure(error);
        }

    }*/
}

