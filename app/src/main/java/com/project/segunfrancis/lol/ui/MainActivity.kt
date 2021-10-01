package com.project.segunfrancis.lol.ui

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.google.android.gms.ads.AdRequest
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.project.segunfrancis.lol.R
import com.project.segunfrancis.lol.databinding.ActivityMainBinding
import com.project.segunfrancis.lol.ui.presentation_util.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val navController: NavController by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
                R.id.nav_about,
                R.id.nav_settings
            ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->

        }
    }

    private fun setupAd() = with(binding) {
        val adRequest = AdRequest.Builder().build()
        mainInclude.adView.loadAd(adRequest)
    }

    private fun displayExitDialog() {
        val dialog = MaterialAlertDialogBuilder(this@MainActivity).apply {
            setMessage("Are you sure you want to exit?")
            setPositiveButton("YES") { dialogInterface, _ ->
                dialogInterface.dismiss()
                super.onBackPressed()
            }
            setNegativeButton("NO") { dialogInterface, _ ->
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
