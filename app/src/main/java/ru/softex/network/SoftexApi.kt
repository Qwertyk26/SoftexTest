package ru.softex.network

import io.reactivex.Observable
import retrofit2.http.GET
import ru.softex.model.Country

interface SoftexApi {

    @GET("test.json")
    fun getCountries(): Observable<ArrayList<Country>>
}
