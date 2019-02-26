package pl.osmalek.bartek.coroutinesworkshop.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.osmalek.bartek.coroutinesworkshop.R
import pl.osmalek.bartek.coroutinesworkshop.data.domain.Film
import pl.osmalek.bartek.coroutinesworkshop.ui.loading.dispatch

class MainActivity : BaseActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setupFilmsRecyclerView()
        observeLoadingStates()
    }

    private fun setupFilmsRecyclerView() {
        val filmAdapter = FilmAdapter(::openDetails)
        with(filmsRecyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = filmAdapter
        }

        launch { filmAdapter.submitList(viewModel.films.await()) }
    }

    private fun observeLoadingStates() {
        viewModel.loadingState.observe(::getLifecycle) {
            loadingView.dispatch(loadingState = it)
        }
    }

    private fun openDetails(film: Film) {
        startActivity(DetailsActivity.intent(this, film))
    }
}
