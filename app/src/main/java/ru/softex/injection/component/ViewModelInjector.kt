package ru.sorftex.injection.component

import dagger.Component
import ru.softex.feature.country.CountryListViewModel
import ru.softex.feature.country.CountryViewModel
import ru.softex.injection.module.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(countryListViewModel: CountryListViewModel)
    fun inject(countryViewModel: CountryViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
    }
}
