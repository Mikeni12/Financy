package mx.mikeni.financy

import android.app.Application
import mx.mikeni.data.di.dataModule
import mx.mikeni.onboarding.di.onboardingModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    private val appModules = listOf(dataModule, onboardingModule)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModules)
        }
    }
}
