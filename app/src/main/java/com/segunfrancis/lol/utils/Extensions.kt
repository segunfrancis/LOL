package com.segunfrancis.lol.utils

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.View
import androidx.annotation.StringRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.segunfrancis.lol.R

fun View.showMessage(
    message: String?,
    @StringRes actionTitle: Int? = null,
    indefiniteDuration: Boolean = false,
    onActionItemClick: (() -> Unit)? = null
) {
    val snackBar = message?.let {
        Snackbar.make(
            this,
            it,
            if (indefiniteDuration) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_LONG
        )
    }
    snackBar?.setBackgroundTint(Color.rgb(0, 151, 167))
    actionTitle?.let {
        snackBar?.setAction(it) {
            onActionItemClick?.invoke()
        }
    }
    snackBar?.show()
}

fun Fragment.share(item: String) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.putExtra(
        Intent.EXTRA_TEXT, "$item \n #LOL \n" +
                "https://play.google.com/store/apps/details?id=com.project.segunfrancis.lol"
    )
    shareIntent.type = "text/plain"
    startActivity(shareIntent)
}

fun Fragment.openWebPage(url: String) {
    val builder: CustomTabsIntent.Builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    builder.setToolbarColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
    customTabsIntent.launchUrl(requireContext(), Uri.parse(url))
}
