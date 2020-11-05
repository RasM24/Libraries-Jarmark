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

	fun replace(fragment: Fragment) {
		fragmentManager.replace(fragment, container)
	}
}