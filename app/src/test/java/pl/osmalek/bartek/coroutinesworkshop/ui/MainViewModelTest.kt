package pl.osmalek.bartek.coroutinesworkshop.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import assertk.assertThat
import assertk.assertions.containsExactly
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import pl.osmalek.bartek.coroutinesworkshop.TestConstants
import pl.osmalek.bartek.coroutinesworkshop.repositories.StarWarsRepository

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var starWarsRepository: StarWarsRepository

    lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        mainViewModel = MainViewModel(starWarsRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `gets films`() = runBlocking {
        whenever(starWarsRepository.getFilms()).thenReturn(listOf(TestConstants.film))

        val films = mainViewModel.films.await()

        assertThat(films).containsExactly(TestConstants.film)
    }
}