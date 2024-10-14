package mx.mikeni.financy

import android.app.Application
import mx.mikeni.data.di.firebaseModule
import mx.mikeni.onboarding.signin.di.signInModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androix.startup.KoinStartup.onKoinStartup

@Suppress("OPT_IN_USAGE")
class MainApplication : Application() {

    private val featuresModules = listOf(firebaseModule, signInModule)

    init {
        onKoinStartup {
            androidLogger()
            androidContext(this@MainApplication)
            modules(featuresModules)
        }
    }
}
