package com.bisa.submissionone.data.source.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bisa.submissionone.data.source.remote.api.ApiConfig
import com.bisa.submissionone.data.source.remote.api.ConfigNetwork.API_KEY
import com.bisa.submissionone.data.source.remote.api.ConfigNetwork.LANGUAGE
import com.bisa.submissionone.data.source.remote.api.ConfigNetwork.PAGE
import com.bisa.submissionone.data.source.remote.response.*
import com.bisa.submissionone.utils.EspressoIdlingResource
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource{

    companion object{

        const val TAG = "RemoteDataSource"
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 1000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource().apply { instance = this }
            }

    }

    private val handler = Handler(Looper.getMainLooper())


    fun getListMovies(): LiveData<ApiResponse<List<ResultsMovieItem>>> {

        EspressoIdlingResource.increment()

        val resultMovies = MutableLiveData<ApiResponse<List<ResultsMovieItem>>>()

        handler.postDelayed({

            val client = ApiConfig.getApiService().getMovies(API_KEY, LANGUAGE, PAGE)

            try {
                client.enqueue(object : Callback<MoviesResponse> {
                    override fun onResponse(
                        call: Call<MoviesResponse>,
                        response: Response<MoviesResponse>
                    ) {

                        if (response.isSuccessful){
                            Log.d(TAG, " Success : ${response.body()?.results?.get(0)?.title}")

                            resultMovies.value = ApiResponse.success(response.body()?.results!!)

                            EspressoIdlingResource.decrement()

                        }else{
                            Log.e(TAG, "onFailure: ${response.message()}")
                        }

                    }

                    override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                        Log.e(TAG, "onFailure: ${t.message.toString()}")
                        resultMovies.value = ApiResponse.error(t.message.toString(), arrayListOf())
                    }

                })

            }catch (e: JSONException){
                e.printStackTrace()
            }



        }, SERVICE_LATENCY_IN_MILLIS)

        return resultMovies

    }

    fun getListTvShows(): LiveData<ApiResponse<List<ResultsTvShowItem>>> {

        EspressoIdlingResource.increment()

        val resultTvShows = MutableLiveData<ApiResponse<List<ResultsTvShowItem>>>()

        handler.postDelayed({
            val client = ApiConfig.getApiService().getTvShows(API_KEY, LANGUAGE, PAGE)

            try {
                client.enqueue(object : Callback<TvShowsResponse> {
                    override fun onResponse(
                        call: Call<TvShowsResponse>,
                        response: Response<TvShowsResponse>
                    ) {
                        if (response.isSuccessful){
                            Log.d(TAG, " Success : ${response.body()?.results?.get(0)?.name}")

                            resultTvShows.value = ApiResponse.success(response.body()?.results!!)

                            EspressoIdlingResource.decrement()

                        }else{
                            Log.e(TAG, "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<TvShowsResponse>, t: Throwable) {
                        Log.e(TAG, "onFailure: ${t.message.toString()}")
                    }

                })
            } catch (e: JSONException){
                e.printStackTrace()
            }

        }, SERVICE_LATENCY_IN_MILLIS)

        return resultTvShows

    }



    fun getDetailCatalogMovie(movieId: String): LiveData<ApiResponse<DetailCatalogMovieResponse>> {

        EspressoIdlingResource.increment()

        val resultDetailMovie = MutableLiveData<ApiResponse<DetailCatalogMovieResponse>>()

        handler.postDelayed({

            val client = ApiConfig.getApiService().getDetailMovie(movieId, API_KEY, LANGUAGE)

            try {
                client.enqueue(object : Callback<DetailCatalogMovieResponse> {
                    override fun onResponse(
                        call: Call<DetailCatalogMovieResponse>,
                        response: Response<DetailCatalogMovieResponse>
                    ) {
                        if (response.isSuccessful){
                            val data = response.body()
                            if (data != null ){
                                Log.d(TAG, "Success detail : ${response.body()?.title}")
                                resultDetailMovie.value = ApiResponse.success(data)
                            }else{
                                resultDetailMovie.value = ApiResponse.empty("Empty", response.body()!!)
                            }
                            EspressoIdlingResource.decrement()

                        }else{
                            Log.e(TAG, "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<DetailCatalogMovieResponse>, t: Throwable) {
                        Log.e(TAG, "onFailure: ${t.message.toString()}")
                        resultDetailMovie.value = ApiResponse.error(t.message.toString(), DetailCatalogMovieResponse("",
                            arrayListOf(),0,"",0, "","",0.0,"")
                        )

                    }

                })
            } catch (e: JSONException){
                e.printStackTrace()
            }



        }, SERVICE_LATENCY_IN_MILLIS)

        return resultDetailMovie

    }

    fun getDetailCatalogTvShow(tvShowId: String): LiveData<ApiResponse<DetailCatalogTvResponse>> {

        EspressoIdlingResource.increment()

        val resultDetailTv = MutableLiveData<ApiResponse<DetailCatalogTvResponse>>()

        handler.postDelayed({

            val client = ApiConfig.getApiService().getDetailTvShow(tvShowId, API_KEY, LANGUAGE)

            try {
                client.enqueue(object : Callback<DetailCatalogTvResponse>{
                    override fun onResponse(
                        call: Call<DetailCatalogTvResponse>,
                        response: Response<DetailCatalogTvResponse>
                    ) {
                        if (response.isSuccessful){
                            val data = response.body()

                            if (data != null){
                                Log.d(TAG, " Success : ${response.body()?.name}")
                                resultDetailTv.value = ApiResponse.success(response.body()!!)
                            }else{
                                resultDetailTv.value = ApiResponse.empty("Empty", response.body()!!)
                            }
                            EspressoIdlingResource.decrement()

                        }else{
                            Log.e(TAG, "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<DetailCatalogTvResponse>, t: Throwable) {
                        Log.e(TAG, "onFailure: ${t.message.toString()}")
                        resultDetailTv.value = ApiResponse.error(t.message.toString(), DetailCatalogTvResponse(
                            arrayListOf(),0,"","","",0.0,"","", arrayListOf())
                        )
                    }

                })
            } catch (e: JSONException){
                e.printStackTrace()
            }



        }, SERVICE_LATENCY_IN_MILLIS)

        return resultDetailTv

    }

    fun getCreditMovieActor(movieId: String): LiveData<ApiResponse<CreditMovieActorResponse>> {

        val resultActorsMovie = MutableLiveData<ApiResponse<CreditMovieActorResponse>>()

        handler.postDelayed({

            val client = ApiConfig.getApiService().getCreditMovie(movieId, API_KEY, LANGUAGE)

            try {
                client.enqueue(object : Callback<CreditMovieActorResponse>{
                    override fun onResponse(
                        call: Call<CreditMovieActorResponse>,
                        response: Response<CreditMovieActorResponse>
                    ) {
                        if (response.isSuccessful){

                            Log.d(TAG, " Success : ${response.body()?.castMovie!![0].name}")



                            resultActorsMovie.value = ApiResponse.success(response.body()!!)

                        }else{
                            Log.e(TAG, "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<CreditMovieActorResponse>, t: Throwable) {
                        Log.e(TAG, "onFailuree: ${t.message.toString()}")
                    }
                })

            } catch (e: JSONException){
                e.printStackTrace()
            }

        }, SERVICE_LATENCY_IN_MILLIS)

        return resultActorsMovie

    }

    fun getCreditTvShowActor(tvId: String): LiveData<ApiResponse<CreditTvShowActorResponse>> {

        val resultActorsTvShow = MutableLiveData<ApiResponse<CreditTvShowActorResponse>>()

        handler.postDelayed({

            val client = ApiConfig.getApiService().getCreditTvShow(tvId, API_KEY, LANGUAGE)

            try{
                client.enqueue(object : Callback<CreditTvShowActorResponse>{
                    override fun onResponse(
                        call: Call<CreditTvShowActorResponse>,
                        response: Response<CreditTvShowActorResponse>
                    ) {
                        if (response.isSuccessful){

                            Log.d(TAG, " Success : ${response.body()?.cast!![0].name}")



                            resultActorsTvShow.value = ApiResponse.success(response.body()!!)

                        }else{
                            Log.e(TAG, "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<CreditTvShowActorResponse>, t: Throwable) {
                        Log.e(TAG, "onFailuree: ${t.message.toString()}")
                    }

                })

            } catch (e: JSONException){
                e.printStackTrace()
            }

        }, SERVICE_LATENCY_IN_MILLIS)

        return resultActorsTvShow

    }


    interface LoadListMoviesCallback{
        fun onListMoviesReceived(moviesResponse: List<ResultsMovieItem>)
    }

    interface LoadListTvShowsCallback{
        fun onListTvShowsReceived(tvShowsResponse: List<ResultsTvShowItem>)
    }

    interface LoadDetailMovieCatalogCallback{
        fun onDetailMovieCatalogReceived(detailCatalogMovie: DetailCatalogMovieResponse)
    }

    interface LoadDetailTvShowCatalogCallback{
        fun onDetailTvShowCatalogReceived(detailCatalogTv: DetailCatalogTvResponse)
    }

    interface LoadCreditMovieActorCallback{
        fun onCreditMovieActorReceived(creditMovieActorResponse: CreditMovieActorResponse)
    }

    interface LoadCreditTvShowActorCallback{
        fun onCreditTvShowActorReceived(creditTvActorResponse: CreditTvShowActorResponse)
    }
}