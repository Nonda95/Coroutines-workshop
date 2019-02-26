package pl.osmalek.bartek.coroutinesworkshop.data

import pl.osmalek.bartek.coroutinesworkshop.data.api.PersonEntity
import pl.osmalek.bartek.coroutinesworkshop.data.api.PlanetEntity
import pl.osmalek.bartek.coroutinesworkshop.data.domain.Person

fun PersonEntity.toDomain(homeWorld: PlanetEntity): Person {
    return Person(name = name, homeWorld = homeWorld.name)
}