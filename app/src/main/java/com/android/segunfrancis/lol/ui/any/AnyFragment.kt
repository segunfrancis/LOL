package com.android.segunfrancis.lol.ui.any

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.segunfrancis.lol.R

class AnyFragment : Fragment() {

    private lateinit var anyViewModel: AnyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        anyViewModel =
            ViewModelProviders.of(this).get(AnyViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_any, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        anyViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}