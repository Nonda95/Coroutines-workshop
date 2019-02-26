package pl.osmalek.bartek.coroutinesworkshop.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.osmalek.bartek.coroutinesworkshop.ui.loading.LoadingState

abstract class BaseViewModel : ViewModel() {
    private val _loadingState = MutableLiveData<LoadingState>()
    val loadingState: LiveData<LoadingState> = _loadingState

    protected fun setLoadingState(loadingState: LoadingState) {
        _loadingState.value = loadingState
    }
}
