package ru.endroad.sample.jarmark.application

import kotlinx.android.synthetic.main.hub_fragment.*
import org.koin.android.ext.android.inject
import ru.endroad.sample.jarmark.R
import ru.endroad.sample.jarmark.router.HubRouter

class HubFragment : BaseFragment() {

	override val layout = R.layout.hub_fragment

	private val router: HubRouter by inject()

	override fun setupViewComponents() {
		setToolbarText(getString(R.string.application_name))

		set_icon_button.setOnClickListener { router.openIconSetScreen() }
		opacity_button.setOnClickListener { router.openOpacityScreen() }
		appearance_button.setOnClickListener { router.openTextAppearanceScreen() }
		panorama_list_button.setOnClickListener { router.openPanorama360List() }
	}
}