package pl.osmalek.bartek.coroutinesworkshop.api

import pl.osmalek.bartek.coroutinesworkshop.data.api.FilmEntity
import pl.osmalek.bartek.coroutinesworkshop.data.api.PersonEntity
import pl.osmalek.bartek.coroutinesworkshop.data.api.PlanetEntity
import pl.osmalek.bartek.coroutinesworkshop.data.api.ResponseEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsService {
    @GET("films")
    fun getFilmsAsync(): Call<ResponseEntity<FilmEntity>>

    @GET("people/{personId}")
    fun getPersonAsync(@Path("personId") characterId: Int): Call<PersonEntity>

    @GET("planets/{planetId}")
    fun getPlanetAsync(@Path("planetId") planetId: Int): Call<PlanetEntity>
}
