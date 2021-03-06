package pl.osmalek.bartek.coroutinesworkshop.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import pl.osmalek.bartek.coroutinesworkshop.R
import pl.osmalek.bartek.coroutinesworkshop.data.domain.Film
import pl.osmalek.bartek.coroutinesworkshop.ui.loading.dispatch

class DetailsActivity : AppCompatActivity() {

    private val film by lazy { intent.getParcelableExtra<Film>(FILM_KEY) }

    private val viewModel by viewModel<DetailsViewModel> { parametersOf(film) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)
        supportActionBar?.title = film.title
        viewModel.fetchCharacters()
        setupCharactersRecyclerView()
        observeLoadingStates()
    }

    private fun observeLoadingStates() {
        viewModel.loadingState.observe(::getLifecycle) {
            loadingView.dispatch(loadingState = it)
        }
    }

    private fun setupCharactersRecyclerView() {
        val peopleAdapter = PeopleAdapter()
        with(charactersRecyclerView) {
            layoutManager = LinearLayoutManager(this@DetailsActivity)
            adapter = peopleAdapter
        }

        viewModel.characters.observe(::getLifecycle) {
            peopleAdapter.submitList(it)
        }
    }

    companion object {
        private const val FILM_KEY = "film"
        fun intent(context: Context, film: Film): Intent =
            Intent(context, DetailsActivity::class.java)
                .putExtra(FILM_KEY, film)
    }
}