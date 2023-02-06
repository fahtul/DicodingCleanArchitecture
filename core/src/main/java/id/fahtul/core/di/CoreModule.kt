package id.fahtul.core.di

import androidx.room.Room
import id.fahtul.core.data.DetailGameRepository
import id.fahtul.core.data.GameRepository
import id.fahtul.core.data.source.local.DetailGameLocalDataSource
import id.fahtul.core.data.source.local.GameLocalDataSource
import id.fahtul.core.data.source.local.room.GameDatabase
import id.fahtul.core.data.source.remote.RemoteDataSource
import id.fahtul.core.data.source.remote.network.ApiService
import id.fahtul.core.domain.repository.IDetailGameRepository
import id.fahtul.core.domain.repository.IGameRepository
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        Interceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("key", "ee1f60ee306e455b8bf2e8aa65f4f707")
                .build()
            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val databaseModule = module {
    factory { get<GameDatabase>().gameDao() }
    factory { get<GameDatabase>().detailGameDao() }

    single {
        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java, "Game.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val repositoryModule = module {
    single { GameLocalDataSource(get()) }
    single { DetailGameLocalDataSource(get()) }

    single { RemoteDataSource(get()) }

    single<IGameRepository> {
        GameRepository(
            get(),
            get()
        )
    }
    single<IDetailGameRepository> {
        DetailGameRepository(
            get(),
            get()
        )
    }
}
