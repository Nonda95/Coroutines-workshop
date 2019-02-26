package pl.osmalek.bartek.coroutinesworkshop.repositories

import assertk.assertThat
import assertk.assertions.containsExactly
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import pl.osmalek.bartek.coroutinesworkshop.TestConstants
import pl.osmalek.bartek.coroutinesworkshop.api.StarWarsService
import pl.osmalek.bartek.coroutinesworkshop.data.api.ResponseEntity

@RunWith(MockitoJUnitRunner::class)
class StarWarsRepositoryTest {

    @Mock
    lateinit var starWarsService: StarWarsService
    @InjectMocks
    lateinit var starWarsRepository: StarWarsRepository

    @Test
    fun `gets films`() = runBlocking {
        whenever(starWarsService.getFilmsAsync()).thenReturn(async {
            ResponseEntity(
                listOf(
                    TestConstants.filmEntity,
                    TestConstants.filmEntity.copy(episodeId = 1)
                )
            )
        })

        val films = starWarsRepository.getFilms()

        assertThat(films).containsExactly(
            TestConstants.film.copy(episodeId = 1),
            TestConstants.film
        )
    }

    @Test
    fun `gets characters`() = runBlocking {
        whenever(starWarsService.getPersonAsync(1)).thenReturn(async { TestConstants.personEntity })
        whenever(starWarsService.getPlanetAsync(2)).thenReturn(async { TestConstants.planetEntity })

        val characters = starWarsRepository.getCharacters(listOf(1))

        assertThat(characters).containsExactly(
            TestConstants.person
        )
    }
}