package com.segunfrancis.lol.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.segunfrancis.lol.R
import com.segunfrancis.lol.databinding.FragmentSettingsBinding
import com.segunfrancis.lol.ui.presentation_util.viewBinding
import com.segunfrancis.lol.utils.openWebPage

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val binding by viewBinding(FragmentSettingsBinding::bind)
    // private var nightMode: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.privacyPolicyText.setOnClickListener {
            openWebPage("https://segunfrancis.github.io")
        }

        /*switchTheme.setOnCheckedChangeListener { compoundButton, b ->
           if (compoundButton.isChecked) {
               AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
               nightMode = AppCompatDelegate.getDefaultNightMode()
           } else {
               AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
               nightMode = AppCompatDelegate.getDefaultNightMode()
           }
       }

       // Saving theme into shared preference
       val preference =
           root.context.getSharedPreferences(Utility.SHARED_PREF_KEY, Activity.MODE_PRIVATE)
       val editor = preference.edit()
       editor.putInt(Utility.APP_THEME, nightMode)
       editor.apply()

       settingsViewModel.text.observe(this, Observer {
           textView.text = it
       })*/
    }
}
