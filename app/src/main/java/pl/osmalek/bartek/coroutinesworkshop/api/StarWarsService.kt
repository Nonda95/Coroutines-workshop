package pl.osmalek.bartek.coroutinesworkshop.api

import kotlinx.coroutines.Deferred
import pl.osmalek.bartek.coroutinesworkshop.data.api.FilmEntity
import pl.osmalek.bartek.coroutinesworkshop.data.api.PersonEntity
import pl.osmalek.bartek.coroutinesworkshop.data.api.PlanetEntity
import pl.osmalek.bartek.coroutinesworkshop.data.api.ResponseEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsService {
    @GET("films")
    fun getFilmsAsync(): Deferred<ResponseEntity<FilmEntity>>

    @GET("people/{personId}")
    fun getPersonAsync(@Path("personId") characterId: Int): Deferred<PersonEntity>

    @GET("planets/{planetId}")
    fun getPlanetAsync(@Path("planetId") planetId: Int): Deferred<PlanetEntity>
}
