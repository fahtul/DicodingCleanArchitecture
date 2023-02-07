package id.fahtul.core.di

import androidx.room.Room
import id.fahtul.core.BuildConfig
import id.fahtul.core.data.DetailGameRepository
import id.fahtul.core.data.GameRepository
import id.fahtul.core.data.source.local.DetailGameLocalDataSource
import id.fahtul.core.data.source.local.GameLocalDataSource
import id.fahtul.core.data.source.local.room.GameDatabase
import id.fahtul.core.data.source.remote.RemoteDataSource
import id.fahtul.core.data.source.remote.network.ApiService
import id.fahtul.core.domain.repository.IDetailGameRepository
import id.fahtul.core.domain.repository.IGameRepository
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
                .addQueryParameter("key", BuildConfig.API_KEY)
                .build()
            val requestBuilder = original.newBuilder()
                .url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }
    single {
        val hostname = "api.rawg.io"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/Vt5/77IBRU8Ic76wffoVpn2hrTRotDK+cuASoGoEzS0=")
            .add(hostname, "sha256/hS5jJ4P+iQUErBkvoWBQOd1T7VOAYlOVegvv1iMzpxA=")
            .add(hostname, "sha256/6i+nf58l8neEnNarZOvxiYfVbt2S2xurswGQQBBMa0U=")
            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .certificatePinner(certificatePinner)
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicodingsubmission".toCharArray())
        val factory= SupportFactory(passphrase)

        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java, "Game.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
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
