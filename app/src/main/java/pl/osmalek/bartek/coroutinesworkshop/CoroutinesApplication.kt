package pl.osmalek.bartek.coroutinesworkshop

import android.app.Application
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.experimental.builder.factory
import pl.osmalek.bartek.coroutinesworkshop.api.StarWarsService
import pl.osmalek.bartek.coroutinesworkshop.data.domain.Film
import pl.osmalek.bartek.coroutinesworkshop.repositories.StarWarsRepository
import pl.osmalek.bartek.coroutinesworkshop.ui.DetailsViewModel
import pl.osmalek.bartek.coroutinesworkshop.ui.MainViewModel
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import timber.log.Timber

class CoroutinesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(ThreadDebugTree())
        startKoin(this, listOf(appModule))
    }
}

private val appModule = module {
    factory<StarWarsRepository>()
    viewModel<MainViewModel>()
    viewModel { (film: Film) -> DetailsViewModel(film, get()) }

    factory<StarWarsService> {
        Retrofit.Builder()
            .baseUrl("https://swapi.co/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
}