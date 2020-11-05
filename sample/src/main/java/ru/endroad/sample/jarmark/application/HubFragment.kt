package ru.endroad.sample.jarmark.application

import ru.endroad.sample.jarmark.R

class HubFragment : BaseFragment() {

	override val layout = R.layout.hub_fragment

	override fun setupViewComponents() {
		setToolbarText(getString(R.string.application_name))
	}
}