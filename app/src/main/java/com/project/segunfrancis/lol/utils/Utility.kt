package com.project.segunfrancis.lol.utils

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.project.segunfrancis.lol.api.Client
import com.project.segunfrancis.lol.api.Service
import com.project.segunfrancis.lol.data.AlternateJokeResponse
import com.project.segunfrancis.lol.data.JokeResponse
import com.project.segunfrancis.lol.data.MiscellaneousJokeResponse
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Utility {
    const val INSTANCE_STATE_KEY = "joke"
    const val SHARED_PREF_KEY = "sharedPref"
    const val APP_THEME = "app_theme_state"
    const val TAG = "segunfrancis.lol.utils"

    /************************** ANY JOKE ********************************/
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
                displaySnackBar(textView, textView, "Network Failure")
                Log.d(TAG, t.localizedMessage)
            }
        })
    }

    /************************ ANY JOKE FROM ALTERNATE URL ***********************/
    fun loadAlternateJoke(textView: TextView, progressBar: ProgressBar) {
        val client = Client
        val apiService = client.getClient()?.create(Service::class.java)
        val call: Call<AlternateJokeResponse> =
            apiService!!.getAlternateJoke("https://official-joke-api.appspot.com/random_joke")
        call.enqueue(object : Callback<AlternateJokeResponse?> {
            override fun onResponse(
                call: Call<AlternateJokeResponse?>,
                response: Response<AlternateJokeResponse?>
            ) {
                val setup = response.body()?.setup
                val punchline = response.body()?.punchline
                textView.text = setup.plus("\n").plus(punchline)
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<AlternateJokeResponse?>, t: Throwable) {
                displaySnackBar(textView, textView, "Network Failure")
                Log.d(TAG, t.localizedMessage)
                progressBar.visibility = View.GONE
            }
        })
    }

    /************************** PROGRAMMING JOKES *******************************/
    fun loadProgrammingJoke(textView: TextView, progressBar: ProgressBar) {
        val client = Client
        val apiService = client.getClient()?.create(Service::class.java)
        val call: Call<JokeResponse> = apiService!!.getProgrammingJoke()
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
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<JokeResponse?>, t: Throwable) {
                displaySnackBar(textView, textView, "Network Failure")
                Log.d(TAG, t.localizedMessage)
                progressBar.visibility = View.GONE
            }
        })
    }

    /************************** DARK JOKES ********************************/
    fun loadDarkJoke(textView: TextView, progressBar: ProgressBar) {
        val client = Client
        val apiService = client.getClient()?.create(Service::class.java)
        val call: Call<JokeResponse> = apiService!!.getDarkJoke()
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
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<JokeResponse?>, t: Throwable) {
                displaySnackBar(textView, textView, "Network Failure")
                Log.d(TAG, t.localizedMessage)
                progressBar.visibility = View.GONE
            }
        })
    }

    /************************** MISCELLANEOUS JOKES ********************************/
    fun loadMiscellaneousJoke(textView: TextView, progressBar: ProgressBar) {
        val client = Client
        val apiService = client.getClient()?.create(Service::class.java)
        val call: Call<JokeResponse> = apiService!!.getMiscellaneousJoke()
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
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<JokeResponse?>, t: Throwable) {
                displaySnackBar(textView, textView, "Network Failure")
                Log.d(TAG, t.localizedMessage)
                progressBar.visibility = View.GONE
            }
        })
    }

    /************************** MISCELLANEOUS JOKES 2 ********************************/
    fun loadMiscellaneousJoke2(textView: TextView, progressBar: ProgressBar) {
        val client = Client
        val apiService = client.getClient()?.create(Service::class.java)
        val call: Call<MiscellaneousJokeResponse> =
            apiService!!.getMiscellaneousJoke2("https://icanhazdadjoke.com/")
        call.enqueue(object : Callback<MiscellaneousJokeResponse?> {
            override fun onResponse(
                call: Call<MiscellaneousJokeResponse?>,
                response: Response<MiscellaneousJokeResponse?>
            ) {
                Log.d(TAG, "Status Code: ${response.body()?.status}")
                val result = response.body()
                val joke = result?.joke
                textView.text = joke
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<MiscellaneousJokeResponse?>, t: Throwable) {
                displaySnackBar(textView, textView, "Network Failure")
                Log.d(TAG, t.localizedMessage)
                progressBar.visibility = View.GONE
            }
        })
    }

    fun displaySnackBar(view: View, textView: TextView, message: String) {
        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
        snackBar.apply {
            setAction("RETRY") {
                loadAnyJoke(textView)
            }
            setActionTextColor(Color.rgb(121, 85, 72))
            setBackgroundTint(Color.rgb(0, 151, 167))
            show()
        }
    }
}