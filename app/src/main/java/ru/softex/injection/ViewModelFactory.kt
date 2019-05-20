package ru.softex.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import ru.softex.feature.country.CountryListViewModel
import ru.softex.model.database.AppDatabase

class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "countries").build()
            @Suppress("UNCHECKED_CAST")
            return CountryListViewModel(db.countryDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}
