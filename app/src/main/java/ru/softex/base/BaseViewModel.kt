package ru.softex.base

import androidx.lifecycle.ViewModel
import ru.softex.feature.country.CountryListViewModel
import ru.softex.feature.country.CountryViewModel
import ru.softex.injection.module.NetworkModule
import ru.sorftex.injection.component.DaggerViewModelInjector
import ru.sorftex.injection.component.ViewModelInjector

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule())
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is CountryListViewModel -> injector.inject(this)
            is CountryViewModel -> injector.inject(this)
        }
    }
}
