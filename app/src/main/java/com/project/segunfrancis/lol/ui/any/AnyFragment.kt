package com.project.segunfrancis.lol.ui.any

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.project.segunfrancis.lol.R
import com.google.android.material.snackbar.Snackbar
import com.project.segunfrancis.lol.databinding.FragmentAnyBinding
import com.project.segunfrancis.lol.ui.model.Joke
import com.project.segunfrancis.lol.ui.model.JokeCategory
import com.project.segunfrancis.lol.ui.presentation_util.NetworkState
import com.project.segunfrancis.lol.ui.presentation_util.viewBinding
import com.project.segunfrancis.lol.utils.Utility.INSTANCE_STATE_KEY
import org.koin.androidx.viewmodel.ext.android.viewModel

class AnyFragment : Fragment(R.layout.fragment_any) {

    private val viewModel: AnyViewModel by viewModel()
    private val binding: FragmentAnyBinding by viewBinding(FragmentAnyBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            binding.textHome.text = savedInstanceState.getString(INSTANCE_STATE_KEY)
        } else {
            //loadAlternateJoke(binding.textHome, binding.progressBar)

            binding.shareFab.setOnClickListener {
                if (binding.textHome.text.isNotBlank()) {
                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "${binding.textHome.text} \n #LOL \n" +
                            "https://play.google.com/store/apps/details?id=com.project.segunfrancis.lol")
                    shareIntent.type = "text/plain"
                    startActivity(shareIntent)
                } else {
                    val snackBar = Snackbar.make(binding.textHome, "Cannot share empty item", Snackbar.LENGTH_LONG)
                    snackBar.setBackgroundTint(Color.rgb(0, 151, 167))
                    snackBar.show()
                }
            }
            /*binding.shuffleButton.setOnClickListener {
                viewModel.getAnyJoke(JokeCategory.ANY.value)
            }*/
        }

        viewModel.anyJokeResponse.observe(viewLifecycleOwner) { state ->
            when(state) {
                is NetworkState.Loading -> handleLoading()
                is NetworkState.Success -> handleSuccess(state.joke)
                is NetworkState.Error -> handleError(state.error)
            }
        }
    }

    private fun handleError(error: Throwable) {

    }

    private fun handleSuccess(joke: Joke) {
        when(joke) {
            is Joke.OneTypeJoke -> { }
            is Joke.TwoTypeJoke -> { }
        }
        binding.progressBar.isGone = true
    }

    private fun handleLoading() = with(binding) {
        progressBar.isVisible = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(INSTANCE_STATE_KEY, binding.textHome.text.toString())
        super.onSaveInstanceState(outState)
    }
}