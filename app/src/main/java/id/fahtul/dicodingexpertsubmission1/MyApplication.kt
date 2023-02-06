package id.fahtul.dicodingexpertsubmission1

import androidx.multidex.MultiDexApplication
import id.fahtul.core.di.databaseModule
import id.fahtul.core.di.networkModule
import id.fahtul.core.di.repositoryModule
import id.fahtul.dicodingexpertsubmission1.di.useCaseModule
import id.fahtul.dicodingexpertsubmission1.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}