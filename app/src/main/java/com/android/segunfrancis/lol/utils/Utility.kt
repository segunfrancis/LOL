package com.android.segunfrancis.lol.utils

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import com.android.segunfrancis.lol.api.Client
import com.android.segunfrancis.lol.api.Service
import com.android.segunfrancis.lol.data.JokeResponse
import com.android.segunfrancis.lol.ui.any.TAG
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Utility {
    companion object {

        const val INSTANCE_STATE_KEY = "joke"

        fun loadAnyJoke(textView: TextView) {
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
                    textView.text = joke.plus(setup).plus("\n").plus(delivery)
                }

                override fun onFailure(call: Call<JokeResponse?>, t: Throwable) {
                    displaySnackBar(textView, textView,"Network Failure")
                    Log.d(TAG, t.localizedMessage)
                }
            })
        }

        fun displaySnackBar(view: View, textView: TextView, message: String) {
            val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
            snackBar.apply {
                setAction("RETRY", View.OnClickListener {
                    loadAnyJoke(textView)
                })
                setActionTextColor(Color.rgb(121, 85, 72))
                setBackgroundTint(Color.rgb(0, 151, 167))
                show()
            }
        }
    }
}