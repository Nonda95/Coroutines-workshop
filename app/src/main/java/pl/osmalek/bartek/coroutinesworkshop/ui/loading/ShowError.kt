package pl.osmalek.bartek.coroutinesworkshop.ui.loading

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun showError(view: View, onRetryClick: () -> Unit) {
    Snackbar.make(view, "Something went wrong", Snackbar.LENGTH_INDEFINITE)
        .setAction("Retry") { onRetryClick() }
        .show()
}