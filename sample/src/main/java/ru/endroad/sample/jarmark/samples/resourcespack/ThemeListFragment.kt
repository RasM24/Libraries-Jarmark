package ru.endroad.sample.jarmark.samples.resourcespack

import kotlinx.android.synthetic.main.resources_pack_fragment.*
import org.koin.android.ext.android.inject
import ru.endroad.sample.jarmark.R
import ru.endroad.sample.jarmark.application.BaseFragment
import ru.endroad.sample.jarmark.router.HubRouter

class ThemeListFragment : BaseFragment() {

	override val layout = R.layout.resources_pack_fragment

	private val router : HubRouter by inject()

	override fun setupViewComponents() {
		setToolbarText(getString(R.string.appearance_title))

		application_theme_button.setOnClickListener { router.openSampleThemeScreen(R.style.ThemeEndroad_Application) }
		libraries_theme_button.setOnClickListener { router.openSampleThemeScreen(R.style.ThemeEndroad_Libraries) }
		sample_theme_button.setOnClickListener { router.openSampleThemeScreen(R.style.ThemeEndroad_Sample) }
		testing_theme_button.setOnClickListener { router.openSampleThemeScreen(R.style.ThemeEndroad_Testing) }
	}
}