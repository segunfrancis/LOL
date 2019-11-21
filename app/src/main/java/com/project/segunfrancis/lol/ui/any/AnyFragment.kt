package com.project.segunfrancis.lol.ui.any

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.project.segunfrancis.lol.R
import com.project.segunfrancis.lol.utils.Utility.Companion.INSTANCE_STATE_KEY
import com.project.segunfrancis.lol.utils.Utility.Companion.loadAlternateJoke
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class AnyFragment : Fragment() {

    private lateinit var anyViewModel: AnyViewModel
    private lateinit var shuffleImage: ExtendedFloatingActionButton
    private lateinit var shareFab: FloatingActionButton
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        anyViewModel =
            ViewModelProviders.of(this).get(AnyViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_any, container, false)
        textView = root.findViewById(R.id.text_home)
        progressBar = root.findViewById(R.id.progressBar)
        shuffleImage = root.findViewById(R.id.imageButton)
        shareFab = root.findViewById(R.id.fab)
        if (savedInstanceState != null) {
            textView.text = savedInstanceState.getString(INSTANCE_STATE_KEY)
        } else {
            loadAlternateJoke(textView, progressBar)
/*        anyViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
            shareFab.setOnClickListener {
                if (textView.text.isNotBlank()) {
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "${textView.text} \n #LOL")
                    shareIntent.type = "text/plain"
                    startActivity(shareIntent)
                } else {
                    Snackbar.make(textView, "Cannot share empty item", Snackbar.LENGTH_LONG).show()
                }
            }
            shuffleImage.setOnClickListener {
                loadAlternateJoke(textView, progressBar)
            }
        }
        return root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(INSTANCE_STATE_KEY, textView.text.toString())
        super.onSaveInstanceState(outState)
    }

    companion object {
        private lateinit var textView: TextView
        const val TAG = "AnyFragment"
    }
}