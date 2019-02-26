package pl.osmalek.bartek.coroutinesworkshop.repositories

import assertk.fail
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import pl.osmalek.bartek.coroutinesworkshop.api.StarWarsService

@RunWith(MockitoJUnitRunner::class)
class StarWarsRepositoryTest {

    @Mock
    lateinit var starWarsService: StarWarsService
    @InjectMocks
    lateinit var starWarsRepository: StarWarsRepository

    @Test
    fun `gets films`() {
        fail("Not implemented")
    }

    @Test
    fun `gets characters`() {
        fail("Not implemented")
    }
}