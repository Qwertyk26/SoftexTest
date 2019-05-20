package ru.softex.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.softex.model.Country

@Dao
interface CountryDao {
    @get:Query("SELECT * FROM country")
    val all: MutableList<Country>

    @Insert
    fun insertAll(vararg countries: Country)

    @Delete
    fun deleteCountry(vararg country: Country)
}
