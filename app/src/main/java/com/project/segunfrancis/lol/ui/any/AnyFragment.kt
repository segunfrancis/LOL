package com.project.segunfrancis.lol.ui.any

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.project.segunfrancis.lol.R
import com.project.segunfrancis.lol.utils.Utility.Companion.INSTANCE_STATE_KEY
import com.project.segunfrancis.lol.utils.Utility.Companion.loadAlternateJoke
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlin.system.exitProcess

class AnyFragment : Fragment() {

    private lateinit var anyViewModel: AnyViewModel
    private lateinit var shuffleImage: ExtendedFloatingActionButton
    private lateinit var shareFab: FloatingActionButton
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val dialog = MaterialAlertDialogBuilder(context).apply {
                    setMessage("Are you sure you want to exit?")
                    setPositiveButton("YES") { dialogInterface, i ->
                        dialogInterface.dismiss()
                        exitProcess(0)
                    }
                    setNegativeButton("NO") { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                }
                dialog.create().show()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this@AnyFragment, onBackPressedCallback)
    }

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
                    val snackBar = Snackbar.make(textView, "Cannot share empty item", Snackbar.LENGTH_LONG)
                    snackBar.setBackgroundTint(Color.rgb(0, 151, 167))
                    snackBar.show()
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