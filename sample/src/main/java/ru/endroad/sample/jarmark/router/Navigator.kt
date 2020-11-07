package ru.endroad.sample.jarmark.router

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class Navigator(
	private val container: Int
) {

	var hubActivity: AppCompatActivity? = null

	private val fragmentManager: FragmentManager
		get() = requireNotNull(hubActivity?.supportFragmentManager)

	fun open(fragment: Fragment) {
		fragmentManager.open(fragment, container)
	}

	fun open(destination: Destination) {
		when (destination) {
			is ActivityDestination -> hubActivity?.run { startActivity(destination.createIntent(baseContext)) }
			else                   -> throw IllegalArgumentException("Destination ${destination::class} is not supported")
		}
	}

	fun replace(fragment: Fragment) {
		fragmentManager.replace(fragment, container)
	}
}