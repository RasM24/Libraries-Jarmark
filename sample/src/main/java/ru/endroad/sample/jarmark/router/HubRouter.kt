package ru.endroad.sample.jarmark.router

import org.koin.java.KoinJavaComponent.inject
import ru.endroad.sample.jarmark.samples.iconset.IconSetFragment

class HubRouter {

	private val navigator by inject(Navigator::class.java)

	fun openIconSetScreen() {
		navigator.open(IconSetFragment())
	}
}