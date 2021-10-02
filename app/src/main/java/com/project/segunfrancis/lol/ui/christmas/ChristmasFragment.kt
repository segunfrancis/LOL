package com.project.segunfrancis.lol.ui.christmas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.project.segunfrancis.lol.R
import com.project.segunfrancis.lol.databinding.ChristmasFragmentBinding
import com.project.segunfrancis.lol.ui.model.Joke
import com.project.segunfrancis.lol.ui.model.JokeCategory
import com.project.segunfrancis.lol.ui.presentation_util.NetworkState
import com.project.segunfrancis.lol.ui.presentation_util.viewBinding
import com.project.segunfrancis.lol.utils.share
import com.project.segunfrancis.lol.utils.showMessage
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ChristmasFragment : Fragment(R.layout.christmas_fragment) {

    private val binding by viewBinding(ChristmasFragmentBinding::bind)
    private val viewModel by viewModel<ChristmasViewModel>()
    private lateinit var jokeString: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()

        setupClickListeners()
    }

    private fun setupObservers() {
        viewModel.christmasJokeResponse.observe(viewLifecycleOwner) { state ->
            when (state) {
                is NetworkState.Loading -> handleLoading()
                is NetworkState.Success -> handleSuccess(state.joke)
                is NetworkState.Error -> handleError(state.error)
            }
            Timber.d("$state")
        }
    }

    private fun setupClickListeners() = with(binding) {
        genericInclude.shareFab.setOnClickListener {
            if (::jokeString.isInitialized && jokeString.isNotBlank()) {
                share(genericInclude.textJoke.text.toString())
            } else {
                it.showMessage("Cannot share empty item")
            }
        }

        genericInclude.shuffleButton.setOnClickListener {
            jokeString = ""
            viewModel.getChristmasJoke(JokeCategory.CHRISTMAS.value)
        }
    }

    private fun handleError(error: Throwable) = with(binding) {
        genericInclude.shuffleButton.showMessage(
            error.localizedMessage,
            R.string.text_retry,
            indefiniteDuration = true
        ) {
            viewModel.getChristmasJoke(JokeCategory.CHRISTMAS.value)
        }
        genericInclude.twoTypeLayout.isGone = true
        genericInclude.textJoke.isGone = true
        genericInclude.progressBar.isGone = true
    }

    private fun handleSuccess(joke: Joke) {
        when (joke) {
            is Joke.OneTypeJoke -> setupOneTypeUI(joke)
            is Joke.TwoTypeJoke -> setupTwoTypeUI(joke)
        }
        binding.genericInclude.progressBar.isGone = true
    }

    private fun setupOneTypeUI(joke: Joke.OneTypeJoke) = with(binding.genericInclude) {
        textJoke.apply {
            text = joke.joke
            isVisible = true
        }
        twoTypeLayout.isGone = true
        jokeString = joke.joke
    }

    private fun setupTwoTypeUI(joke: Joke.TwoTypeJoke) = with(binding.genericInclude) {
        textSetup.text = joke.setup
        textDelivery.text = joke.delivery
        twoTypeLayout.isVisible = true
        textJoke.isGone = true
        jokeString = joke.setup.plus("\n").plus(joke.delivery)
    }

    private fun handleLoading() = with(binding.genericInclude) {
        progressBar.isVisible = true
        twoTypeLayout.isGone = true
        textJoke.isGone = true
    }
}