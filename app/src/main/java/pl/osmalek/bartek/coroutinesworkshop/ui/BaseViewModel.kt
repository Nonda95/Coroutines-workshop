package pl.osmalek.bartek.coroutinesworkshop.ui

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import pl.osmalek.bartek.coroutinesworkshop.ui.loading.LoadingState
import timber.log.Timber
import kotlin.coroutines.resume

abstract class BaseViewModel : ViewModel(), CoroutineScope by MainScope() {
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> = _loadingState

    private fun setLoadingState(loadingState: LoadingState) {
        _loadingState.value = loadingState
    }

    protected suspend fun <T> loadWithRetry(function: suspend () -> T): T = coroutineScope {
        setLoadingState(LoadingState.Loading)
        try {
            val result = function()
            setLoadingState(LoadingState.Idle)
            result
        } catch (e: Exception) {
            Timber.d("Error during loading")
            suspendCancellableCoroutine<T> { continuation ->
                setLoadingState(LoadingState.Error {
                    launch {
                        continuation.resume(loadWithRetry(function))
                    }
                })
            }
        }
    }

    @CallSuper
    override fun onCleared() {
        coroutineContext.cancelChildren()
    }
}
