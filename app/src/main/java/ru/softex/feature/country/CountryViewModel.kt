package ru.softex.feature.country

import androidx.lifecycle.MutableLiveData
import ru.softex.base.BaseViewModel
import ru.softex.model.Country

class CountryViewModel : BaseViewModel() {
    private val countryName = MutableLiveData<String>()
    private val countryImage = MutableLiveData<String>()

    fun bind(country: Country) {
        countryName.value = country.name
        countryImage.value = country.image
    }

    fun getCountryName(): MutableLiveData<String> {
        return countryName
    }

    fun getImageUrl(): MutableLiveData<String> {
        return countryImage
    }

}
