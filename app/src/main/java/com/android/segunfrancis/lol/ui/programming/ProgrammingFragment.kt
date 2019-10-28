package com.android.segunfrancis.lol.ui.programming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.segunfrancis.lol.R

class ProgrammingFragment : Fragment() {

    private lateinit var programmingViewModel: ProgrammingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        programmingViewModel =
            ViewModelProviders.of(this).get(ProgrammingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_programming, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        programmingViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}