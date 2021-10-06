package com.segunfrancis.lol.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.segunfrancis.lol.R
import com.segunfrancis.lol.databinding.ActivityMainBinding
import com.segunfrancis.lol.ui.presentation_util.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupTestDevice()
        setupAd()

        setSupportActionBar(binding.mainInclude.toolbar)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_any,
                R.id.nav_programming,
                R.id.nav_dark,
                R.id.nav_miscellaneous,
                R.id.nav_pun,
                R.id.nav_christmas,
                R.id.nav_spooky,
                R.id.nav_about,
                R.id.nav_settings
            ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    private fun setupAd() = with(binding) {
        val adRequest = AdRequest.Builder().build()
        mainInclude.adView.loadAd(adRequest)
    }

    private fun setupTestDevice() {
        // Enable test ads
        val request = RequestConfiguration.Builder()
            .setTestDeviceIds(listOf("8352585357BD24FD03F59A5EDAB92679")).build()
        MobileAds.setRequestConfiguration(request)
    }

    private fun displayExitDialog() {
        val dialog = MaterialAlertDialogBuilder(this@MainActivity).apply {
            setMessage(resources.getString(R.string.are_you_sure_you_want_to_exit))
            setPositiveButton(resources.getString(R.string.yes)) { dialogInterface, _ ->
                dialogInterface.dismiss()
                super.onBackPressed()
            }
            setNegativeButton(resources.getString(R.string.no)) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
        }
        dialog.create().show()
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.nav_any) {
            displayExitDialog()
        } else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
