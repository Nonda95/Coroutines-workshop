package pl.osmalek.bartek.coroutinesworkshop.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.osmalek.bartek.coroutinesworkshop.data.domain.Film
import pl.osmalek.bartek.coroutinesworkshop.data.domain.Person
import pl.osmalek.bartek.coroutinesworkshop.repositories.StarWarsRepository
import pl.osmalek.bartek.coroutinesworkshop.ui.loading.LoadingState
import timber.log.Timber

class DetailsViewModel(
    private val film: Film,
    private val starWarsRepository: StarWarsRepository
) : BaseViewModel() {
    private val _characters = MutableLiveData<List<Person>>()

    val characters: LiveData<List<Person>>
        get() = _characters

    fun fetchCharacters() {
        if (characters.value == null && loadingState.value != LoadingState.Loading) {
            setLoadingState(LoadingState.Loading)
            Timber.d("Fetching characters")
            starWarsRepository.getCharacters(
                charactersIds = film.charactersIds,
                onCharactersFetched = {
                    Timber.d("Characters fetched: %s", it)
                    _characters.postValue(it)
                    setLoadingState(LoadingState.Idle)
                },
                onError = {
                    Timber.d("Error during characters fetch")
                    setLoadingState(LoadingState.Error(::fetchCharacters))
                }
            )
        }
    }
}
