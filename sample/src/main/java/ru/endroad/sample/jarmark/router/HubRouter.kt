package ru.endroad.sample.jarmark.router

import org.koin.java.KoinJavaComponent.inject
import ru.endroad.sample.jarmark.samples.iconset.IconSetFragment
import ru.endroad.sample.jarmark.samples.opacity.OpacityFragment
import ru.endroad.sample.jarmark.samples.textappearance.TextAppearanceFragment

class HubRouter {

	private val navigator by inject(Navigator::class.java)

	fun openIconSetScreen() {
		navigator.open(IconSetFragment())
	}

	fun openOpacityScreen() {
		navigator.open(OpacityFragment())
	}

	fun openTextAppearenceScreen() {
		navigator.open(TextAppearanceFragment())
	}
}