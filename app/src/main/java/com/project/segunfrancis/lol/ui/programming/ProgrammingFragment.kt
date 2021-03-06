package com.project.segunfrancis.lol.ui.programming

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
import com.project.segunfrancis.lol.utils.Utility.loadProgrammingJoke

class ProgrammingFragment : Fragment() {

    private lateinit var textView: TextView
    private lateinit var shuffleImage: ExtendedFloatingActionButton
    private lateinit var shareFab: FloatingActionButton
    private lateinit var programmingViewModel: ProgrammingViewModel
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        programmingViewModel =
            ViewModelProviders.of(this).get(ProgrammingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_programming, container, false)
        textView = root.findViewById(R.id.text_programming_joke)
        progressBar = root.findViewById(R.id.progressBar)
        shuffleImage = root.findViewById(R.id.imageButton)
        shareFab = root.findViewById(R.id.fab)
        if (savedInstanceState != null) {
            textView.text = savedInstanceState.getString(Utility.INSTANCE_STATE_KEY)
        } else {
            loadProgrammingJoke(textView, progressBar)
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
                loadProgrammingJoke(textView, progressBar)
            }
        }
        return root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(Utility.INSTANCE_STATE_KEY, textView.text.toString())
        super.onSaveInstanceState(outState)
    }
}