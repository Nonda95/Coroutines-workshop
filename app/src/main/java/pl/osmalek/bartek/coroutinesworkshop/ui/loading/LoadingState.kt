package pl.osmalek.bartek.coroutinesworkshop.ui.loading

import androidx.core.widget.ContentLoadingProgressBar

sealed class LoadingState {
    object Idle : LoadingState()
    object Loading : LoadingState()
    data class Error(val errorAction: () -> Unit) : LoadingState()
}

fun ContentLoadingProgressBar.dispatch(loadingState: LoadingState) {
    when (loadingState) {
        LoadingState.Idle -> hide()
        LoadingState.Loading -> show()
        is LoadingState.Error -> {
            hide()
            showError(this, loadingState.errorAction)
        }
    }
}