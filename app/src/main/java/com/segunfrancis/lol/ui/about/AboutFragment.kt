package com.segunfrancis.lol.ui.about

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.segunfrancis.lol.R
import com.segunfrancis.lol.databinding.FragmentAboutBinding
import com.segunfrancis.lol.ui.presentation_util.viewBinding

class AboutFragment : Fragment(R.layout.fragment_about) {

    private val binding by viewBinding(FragmentAboutBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
