package com.android.segunfrancis.lol.ui.any

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.segunfrancis.lol.api.Client
import com.android.segunfrancis.lol.api.Service
import com.android.segunfrancis.lol.data.JokeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnyViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {

        val client = Client
        val apiService = client.getClient()?.create(Service::class.java)
        val call: Call<JokeResponse> = apiService!!.getAnyJoke()
        call.enqueue(object : Callback<JokeResponse?> {
            override fun onResponse(
                call: Call<JokeResponse?>,
                response: Response<JokeResponse?>
            ) {
                val result = response.body()
                val joke = result?.joke
                val setup = result?.setup
                val delivery = result?.delivery
                value = joke.plus(setup).plus("\n").plus(delivery)
            }

            override fun onFailure(call: Call<JokeResponse?>, t: Throwable) {
                value = t.localizedMessage
            }
        })
    }
    val text: LiveData<String> = _text
}