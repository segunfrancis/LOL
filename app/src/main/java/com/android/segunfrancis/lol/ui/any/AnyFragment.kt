package com.android.segunfrancis.lol.ui.any

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.segunfrancis.lol.R
import com.android.segunfrancis.lol.api.Client
import com.android.segunfrancis.lol.api.Service
import com.android.segunfrancis.lol.data.JokeResponse
import com.android.segunfrancis.lol.utils.Utility.Companion.displaySnackBar
import com.android.segunfrancis.lol.utils.Utility.Companion.loadAnyJoke
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG = "AnyFragment"
class AnyFragment : Fragment() {

    private lateinit var anyViewModel: AnyViewModel
    private lateinit var textView: TextView
    private lateinit var shuffleImage: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        anyViewModel =
            ViewModelProviders.of(this).get(AnyViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_any, container, false)
        textView = root.findViewById(R.id.text_home)
        shuffleImage = root.findViewById(R.id.imageButton)
        anyViewModel.text.observe(this, Observer {
            textView.text = it
        })
        shuffleImage.setOnClickListener {
            loadAnyJoke(textView)
        }
        return root
    }
}