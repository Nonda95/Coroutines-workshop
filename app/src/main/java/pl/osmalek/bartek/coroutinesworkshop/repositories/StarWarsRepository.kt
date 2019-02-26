package pl.osmalek.bartek.coroutinesworkshop.repositories

import pl.osmalek.bartek.coroutinesworkshop.api.StarWarsService
import pl.osmalek.bartek.coroutinesworkshop.data.api.FilmEntity
import pl.osmalek.bartek.coroutinesworkshop.data.domain.Film
import pl.osmalek.bartek.coroutinesworkshop.data.domain.Person
import pl.osmalek.bartek.coroutinesworkshop.data.toDomain
import pl.osmalek.bartek.coroutinesworkshop.data.toId
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StarWarsRepository(
    private val starWarsService: StarWarsService
) {

    fun getFilms(
        onFilmsFetched: (List<Film>) -> Unit,
        onError: () -> Unit
    ) {
        starWarsService.getFilmsAsync().enqueue(
            callback(
                onSuccess = {
                    val films = it.results.map(FilmEntity::toDomain).sortedBy(Film::episodeId)
                    onFilmsFetched(films)
                },
                onError = onError
            )
        )
    }

    fun getCharacters(
        charactersIds: List<Int>,
        onCharactersFetched: (List<Person>) -> Unit,
        onError: () -> Unit
    ) {
        val characters = mutableListOf<Person>()
        charactersIds.forEach {
            starWarsService.getPersonAsync(it).enqueue(
                callback(
                    onSuccess = { character ->
                        starWarsService.getPlanetAsync(character.homeWorldUrl.toId())
                            .enqueue(
                                callback(
                                    onSuccess = { homeWorld ->
                                        synchronized(characters) {
                                            characters.add(character.toDomain(homeWorld))
                                            if (characters.size == charactersIds.size) {
                                                onCharactersFetched(characters)
                                            }
                                        }
                                    },
                                    onError = onError
                                )
                            )
                    },
                    onError = onError
                )
            )
        }
    }
}

private inline fun <reified T> callback(
    crossinline onSuccess: (T) -> Unit,
    crossinline onError: () -> Unit = {}
): Callback<T> {
    return object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            onError()
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            onSuccess(response.body()!!)
        }

    }
}