package pl.osmalek.bartek.coroutinesworkshop.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import assertk.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
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
        mainViewModel = MainViewModel(starWarsRepository)
    }

    @Test
    fun `gets films`() {
        fail("Not implemented")
    }
}