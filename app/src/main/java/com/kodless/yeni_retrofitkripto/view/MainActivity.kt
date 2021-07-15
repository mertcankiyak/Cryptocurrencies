package com.kodless.yeni_retrofitkripto.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodless.yeni_retrofitkripto.R
import com.kodless.yeni_retrofitkripto.adapter.CryptoAdapter
import com.kodless.yeni_retrofitkripto.databinding.ActivityMainBinding
import com.kodless.yeni_retrofitkripto.model.CryptoModel
import com.kodless.yeni_retrofitkripto.service.CryptoAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    private val BASE_URL = "https://api.nomics.com/v1/"
    private var cryptoModelList: ArrayList<CryptoModel>? = null
    lateinit var binding: ActivityMainBinding
    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        compositeDisposable = CompositeDisposable()
        loadData()
    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CryptoAPI::class.java)

        compositeDisposable?.add(
            retrofit.getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(this::handleResponse)
        )
    }

    private fun handleResponse(cryptoList:List<CryptoModel>){
        cryptoModelList = ArrayList(cryptoList)
        val cryptoAdapter = CryptoAdapter(cryptoModelList!!)
        binding.recyclerView.layoutManager =LinearLayoutManager(this@MainActivity)
        binding.recyclerView.adapter =cryptoAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }

}
