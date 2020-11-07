package ru.endroad.sample.jarmark.samples.panorama

import kotlinx.android.synthetic.main.panorama360list_fragment.*
import org.koin.android.ext.android.inject
import ru.endroad.sample.jarmark.R
import ru.endroad.sample.jarmark.application.BaseFragment
import ru.endroad.sample.jarmark.router.HubRouter

class Panoramas360ListFragment : BaseFragment() {

	override val layout = R.layout.panorama360list_fragment

	private val adapter = PlaceListAdapter(::onPanoramaClick)
	private val router: HubRouter by inject()

	override fun setupViewComponents() {
		setToolbarText(getString(R.string.panorama_list_title))
		list.adapter = adapter
		list.itemAnimator = null
		list.setHasFixedSize(true)

		adapter.items = PanoramaDataSource(requireContext()).getList()
	}

	private fun onPanoramaClick(panorama: Panorama) = router.open360Panorama(panorama.name)
}