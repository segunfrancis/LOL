package com.android.segunfrancis.lol.ui.settings

import android.app.Activity
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.segunfrancis.lol.R
import com.android.segunfrancis.lol.utils.Utility
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsFragment : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
            ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        val switchTheme: SwitchMaterial = root.findViewById(R.id.switch_theme)
        // Theme state
        val nightMode = AppCompatDelegate.getDefaultNightMode()
        switchTheme.isChecked = nightMode == AppCompatDelegate.MODE_NIGHT_YES

        // Saving theme into shared preference
        val preference = root.context.getSharedPreferences(Utility.SHARED_PREF_KEY, Activity.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putInt(Utility.APP_THEME, nightMode)
        editor.apply()

        switchTheme.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
/*        settingsViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        return root
    }
}