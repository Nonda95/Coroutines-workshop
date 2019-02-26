package pl.osmalek.bartek.coroutinesworkshop.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import pl.osmalek.bartek.coroutinesworkshop.api.StarWarsService
import pl.osmalek.bartek.coroutinesworkshop.data.api.FilmEntity
import pl.osmalek.bartek.coroutinesworkshop.data.domain.Film
import pl.osmalek.bartek.coroutinesworkshop.data.domain.Person
import pl.osmalek.bartek.coroutinesworkshop.data.toDomain
import pl.osmalek.bartek.coroutinesworkshop.data.toId

class StarWarsRepository(
    private val starWarsService: StarWarsService
) {

    suspend fun getFilms(): List<Film> = withContext(Dispatchers.IO) {
        val responseEntity = starWarsService.getFilmsAsync().await()
        responseEntity.results.map(FilmEntity::toDomain).sortedBy(Film::episodeId)
    }

    suspend fun getCharacters(charactersIds: List<Int>): List<Person> = withContext(Dispatchers.IO) {
        val characters = charactersIds.map { starWarsService.getPersonAsync(it) }.awaitAll()
        characters.map { starWarsService.getPlanetAsync(it.homeWorldUrl.toId()) }
            .awaitAll()
            .zip(characters) { homeWorld, character -> character.toDomain(homeWorld) }
    }
}