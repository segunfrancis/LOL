package com.android.segunfrancis.lol.ui.any

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.segunfrancis.lol.R
import com.android.segunfrancis.lol.api.Client
import com.android.segunfrancis.lol.api.Service
import com.android.segunfrancis.lol.data.JokeResponse
import com.android.segunfrancis.lol.utils.Utility
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnyFragment : Fragment() {

    private lateinit var anyViewModel: AnyViewModel
    private lateinit var textView: TextView
    private lateinit var imageButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        anyViewModel =
            ViewModelProviders.of(this).get(AnyViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_any, container, false)
        textView = root.findViewById(R.id.text_home)
        imageButton = root.findViewById(R.id.imageButton)
        loadAnyJoke()
        /*anyViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        imageButton.setOnClickListener {
            loadAnyJoke()
        }
        return root
    }

    private fun loadAnyJoke() {
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
                Utility.displaySnackBar(textView, t.localizedMessage)
            }
        })
    }
}