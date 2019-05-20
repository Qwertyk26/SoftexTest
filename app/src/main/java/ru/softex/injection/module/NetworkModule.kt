package ru.softex.injection.module

import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.sorftex.BuildConfig
import ru.softex.network.SoftexApi
import java.util.concurrent.TimeUnit

@Module
open class NetworkModule {

    @Provides
    @Reusable
    internal fun provideSoftextApi(retrofit: Retrofit): SoftexApi {
        return retrofit.create(SoftexApi::class.java)
    }

    @Provides
    @Reusable
    internal fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .addNetworkInterceptor(logging)
            .build()
    }

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    internal fun provideRetrofitInterface(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    companion object {
        const val TIMEOUT_SECONDS = 60
    }
}
