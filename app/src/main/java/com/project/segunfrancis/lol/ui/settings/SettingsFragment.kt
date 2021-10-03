package com.project.segunfrancis.lol.ui.settings

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.project.segunfrancis.lol.R

class SettingsFragment : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel
    // private var nightMode: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
            ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        // val switchTheme: SwitchMaterial = root.findViewById(R.id.switch_theme)
        val policyText: TextView = root.findViewById(R.id.privacy_policy_text)

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

        policyText.setOnClickListener {
            val policyUrl = "https://segunfrancis.github.io"
            val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            builder.setToolbarColor(resources.getColor(R.color.colorPrimary))
            customTabsIntent.launchUrl(root.context, Uri.parse(policyUrl))
        }
        return root
    }
}
