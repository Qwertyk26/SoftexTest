package ru.softex.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.softex.model.Country
import ru.softex.model.CountryDao

@Database(entities = [Country::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}
