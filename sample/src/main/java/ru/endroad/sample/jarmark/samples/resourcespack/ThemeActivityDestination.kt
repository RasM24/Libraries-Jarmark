package ru.endroad.sample.jarmark.samples.resourcespack

import android.content.Context
import android.content.Intent
import ru.endroad.sample.jarmark.router.ActivityDestination

class ThemeActivityDestination(
	private val themeRes: Int,
) : ActivityDestination {

	override fun createIntent(context: Context): Intent =
		ThemeActivity.createIntent(context, themeRes)
}