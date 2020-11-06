package ru.endroad.sample.jarmark.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.endroad.sample.jarmark.router.moduleNavigation

class App : Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidContext(this@App)
			modules(moduleNavigation)
		}
	}
}