package com.android.segunfrancis.lol.ui.dark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.segunfrancis.lol.R

class DarkFragment : Fragment() {

    private lateinit var darkViewModel: DarkViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        darkViewModel =
            ViewModelProviders.of(this).get(DarkViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dark, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        darkViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}