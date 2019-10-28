package com.android.segunfrancis.lol.ui.miscellaneous

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.segunfrancis.lol.R

class MiscellaneousFragment : Fragment() {

    private lateinit var miscellaneousViewModel: MiscellaneousViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        miscellaneousViewModel =
            ViewModelProviders.of(this).get(MiscellaneousViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_miscellaneous, container, false)
        val textView: TextView = root.findViewById(R.id.text_tools)
        miscellaneousViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}