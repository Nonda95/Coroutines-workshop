package pl.osmalek.bartek.coroutinesworkshop

import pl.osmalek.bartek.coroutinesworkshop.data.api.FilmEntity
import pl.osmalek.bartek.coroutinesworkshop.data.api.PersonEntity
import pl.osmalek.bartek.coroutinesworkshop.data.api.PlanetEntity
import pl.osmalek.bartek.coroutinesworkshop.data.domain.Film
import pl.osmalek.bartek.coroutinesworkshop.data.domain.Person

object TestConstants {
    val filmEntity = FilmEntity(
        name = "The Phantom Menace",
        episodeId = 3,
        charactersUrls = listOf(
            "https://swapi.co/api/people/2",
            "https://swapi.co/api/people/3"
        )
    )
    val film = Film(
        title = "The Phantom Menace",
        episodeId = 3,
        charactersIds = listOf(2, 3)
    )

    val personEntity = PersonEntity("Han Solo", "https://swapi.co/api/planets/2")
    val person = Person("Han Solo", "unknown")

    val planetEntity = PlanetEntity("unknown")
}