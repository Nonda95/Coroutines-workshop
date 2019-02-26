package pl.osmalek.bartek.coroutinesworkshop.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.osmalek.bartek.coroutinesworkshop.data.domain.Film
import pl.osmalek.bartek.coroutinesworkshop.repositories.StarWarsRepository
import pl.osmalek.bartek.coroutinesworkshop.ui.loading.LoadingState
import timber.log.Timber

class MainViewModel(
    private val starWarsRepository: StarWarsRepository
) : BaseViewModel() {

    private val _films = MutableLiveData<List<Film>>()
    val films: LiveData<List<Film>>
        get() = _films

    fun fetchFilms() {
        if (films.value == null && loadingState.value != LoadingState.Loading) {
            setLoadingState(LoadingState.Loading)
            Timber.d("Fetching films")
            starWarsRepository.getFilms(
                onFilmsFetched = {
                    _films.postValue(it)
                    setLoadingState(LoadingState.Idle)
                    Timber.d("Films fetched: %s", it)
                },
                onError = {
                    setLoadingState(LoadingState.Error(::fetchFilms))
                    Timber.d("Error during films fetch")
                }
            )
        }
    }
}