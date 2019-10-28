package com.android.segunfrancis.lol.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

class Utility {
    companion object {
        fun displaySnackBar(view: View, message: String) {
            Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
        }
    }
}