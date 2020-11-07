package ru.endroad.sample.jarmark.router

import org.koin.java.KoinJavaComponent.inject
import ru.endroad.sample.jarmark.samples.iconset.IconSetFragment
import ru.endroad.sample.jarmark.samples.opacity.OpacityFragment
import ru.endroad.sample.jarmark.samples.textappearance.TextAppearanceFragment
import ru.endroad.sample.jarmark.samples.panorama.Panoramas360ListFragment
import ru.endroad.sample.jarmark.samples.panorama.PanoramicPlaceFragment

class HubRouter {

	private val navigator by inject(Navigator::class.java)

	fun openIconSetScreen() {
		navigator.open(IconSetFragment())
	}

	fun openOpacityScreen() {
		navigator.open(OpacityFragment())
	}

	fun openTextAppearanceScreen() {
		navigator.open(TextAppearanceFragment())
	}

	fun openPanorama360List() {
		navigator.open(Panoramas360ListFragment())
	}

	fun open360Panorama(name: String) {
		navigator.open(PanoramicPlaceFragment.create(name))
	}
}