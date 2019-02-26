package pl.osmalek.bartek.coroutinesworkshop.ui

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import pl.osmalek.bartek.coroutinesworkshop.data.domain.Film
import pl.osmalek.bartek.coroutinesworkshop.repositories.StarWarsRepository
import timber.log.Timber

class DetailsViewModel(
    private val film: Film,
    private val starWarsRepository: StarWarsRepository
) : BaseViewModel() {

    val characters = async(start = CoroutineStart.LAZY) {
        Timber.d("Fetching characters")
        val characters = loadWithRetry {
            starWarsRepository.getCharacters(film.charactersIds)
        }
        Timber.d("Characters fetched: %s", characters)
        characters
    }
}
