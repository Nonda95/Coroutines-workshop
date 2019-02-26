package pl.osmalek.bartek.coroutinesworkshop.ui

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import pl.osmalek.bartek.coroutinesworkshop.repositories.StarWarsRepository
import timber.log.Timber

class MainViewModel(
    private val starWarsRepository: StarWarsRepository
) : BaseViewModel() {

    val films = async(start = CoroutineStart.LAZY) {
        Timber.d("Fetching films")
        val films = loadWithRetry {
            starWarsRepository.getFilms()
        }
        Timber.d("Films fetched: %s", films)
        films
    }
}