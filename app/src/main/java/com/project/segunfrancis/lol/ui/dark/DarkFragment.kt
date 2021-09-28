package com.project.segunfrancis.lol.ui.dark

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.project.segunfrancis.lol.R
import com.project.segunfrancis.lol.utils.Utility
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.project.segunfrancis.lol.utils.Utility.loadDarkJoke

class DarkFragment : Fragment() {

    private lateinit var darkViewModel: DarkViewModel
    private lateinit var textView: TextView
    private lateinit var shuffleImage: ExtendedFloatingActionButton
    private lateinit var shareFab: FloatingActionButton
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        darkViewModel =
            ViewModelProviders.of(this).get(DarkViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dark, container, false)
        textView = root.findViewById(R.id.text_dark_joke)
        progressBar = root.findViewById(R.id.progressBar)
        shuffleImage = root.findViewById(R.id.imageButton)
        shareFab = root.findViewById(R.id.shareFab)
        if (savedInstanceState != null) {
            textView.text = savedInstanceState.getString(Utility.INSTANCE_STATE_KEY)
        } else {
            loadDarkJoke(textView, progressBar)
            shareFab.setOnClickListener {
                if (textView.text.isNotBlank()) {
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "${textView.text} \n #LOL \n" +
                            "https://play.google.com/store/apps/details?id=com.project.segunfrancis.lol")
                    shareIntent.type = "text/plain"
                    startActivity(shareIntent)
                } else {
                    val snackBar = Snackbar.make(textView, "Cannot share empty item", Snackbar.LENGTH_LONG)
                    snackBar.setBackgroundTint(Color.rgb(0, 151, 167))
                    snackBar.show()
                }
            }
            shuffleImage.setOnClickListener {
                loadDarkJoke(textView, progressBar)
            }
        }
        return root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(Utility.INSTANCE_STATE_KEY, textView.text.toString())
        super.onSaveInstanceState(outState)
    }
}