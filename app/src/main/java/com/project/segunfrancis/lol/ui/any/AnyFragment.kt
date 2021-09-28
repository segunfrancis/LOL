package com.project.segunfrancis.lol.ui.any

import android.os.Bundle
import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.project.segunfrancis.lol.R
import com.project.segunfrancis.lol.databinding.FragmentAnyBinding
import com.project.segunfrancis.lol.ui.model.Joke
import com.project.segunfrancis.lol.ui.model.JokeCategory
import com.project.segunfrancis.lol.ui.presentation_util.NetworkState
import com.project.segunfrancis.lol.ui.presentation_util.viewBinding
import com.project.segunfrancis.lol.utils.share
import com.project.segunfrancis.lol.utils.showMessage
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class AnyFragment : Fragment(R.layout.fragment_any) {

    private val viewModel: AnyViewModel by viewModel()
    private val binding: FragmentAnyBinding by viewBinding(FragmentAnyBinding::bind)
    private lateinit var jokeString: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()

        setupClickListeners()
    }

    private fun setupObservers() {
        viewModel.anyJokeResponse.observe(viewLifecycleOwner) { state ->
            when (state) {
                is NetworkState.Loading -> handleLoading()
                is NetworkState.Success -> handleSuccess(state.joke)
                is NetworkState.Error -> handleError(state.error)
            }
            Timber.d("$state")
        }
    }

    private fun setupClickListeners() {
        binding.genericInclude.shareFab.setOnClickListener {
            if (::jokeString.isInitialized && jokeString.isNotBlank()) {
                share(binding.genericInclude.textJoke.text.toString())
            } else {
                it.showMessage("Cannot share empty item")
            }
        }
    }

    private fun handleError(error: Throwable) = with(binding) {
        root.showMessage(error.localizedMessage, R.string.text_retry, indefiniteDuration = true) {
            viewModel.getAnyJoke(JokeCategory.ANY.value)
        }
        genericInclude.twoTypeLayout.isGone = true
        genericInclude.textJoke.isGone = true
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