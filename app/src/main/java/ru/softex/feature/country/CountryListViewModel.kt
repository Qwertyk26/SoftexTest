package ru.softex.feature.country

import android.view.View
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.sorftex.R
import ru.softex.base.BaseViewModel
import ru.softex.model.Country
import ru.softex.model.CountryDao
import ru.softex.network.SoftexApi
import javax.inject.Inject

class CountryListViewModel(private val countryDao: CountryDao) : BaseViewModel() {
    @Inject
    lateinit var softexApi: SoftexApi
    val countriesAdapter: CountriesAdapter = CountriesAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadCountries() }
    private lateinit var subscription: Disposable

    init {
        loadCountries()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadCountries() {
        subscription = Observable.fromCallable { countryDao.all }
            .concatMap { dbCountryList ->
                if (dbCountryList.isEmpty())
                    softexApi.getCountries().concatMap { apiCountryList ->
                        countryDao.insertAll(*apiCountryList.toTypedArray())
                        Observable.just(apiCountryList)
                    }
                else
                    Observable.just(dbCountryList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveCountryListStart() }
            .doOnTerminate { onRetrieveCountryListFinish() }
            .subscribe(
                { result -> onRetrieveCountryListSuccess(result) },
                { onRetrievePostListError() }
            )
    }

    fun deleteCountry(position: Int) {
        subscription = Observable.fromCallable {
            countryDao.deleteCountry(countriesAdapter.countryList[position])
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                countriesAdapter.removeAt(position)
            }

    }

    private fun onRetrieveCountryListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveCountryListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveCountryListSuccess(countryList: MutableList<Country>) {
        countriesAdapter.updatePostList(countryList)
    }

    private fun onRetrievePostListError() {
        errorMessage.value = R.string.error_connection
    }

}
