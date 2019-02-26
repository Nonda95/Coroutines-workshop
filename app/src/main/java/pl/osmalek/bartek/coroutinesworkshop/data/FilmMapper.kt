package pl.osmalek.bartek.coroutinesworkshop.data

import pl.osmalek.bartek.coroutinesworkshop.data.api.FilmEntity
import pl.osmalek.bartek.coroutinesworkshop.data.domain.Film

fun FilmEntity.toDomain(): Film {
    return Film(
        title = name,
        episodeId = episodeId,
        charactersIds = charactersUrls.map { it.toId() }
    )
}

fun String.toId(): Int {
    return split("/").last(String::isNotEmpty).toInt()
}
