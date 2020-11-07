package ru.endroad.sample.jarmark.samples.panorama

import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.panorama360_viewer_fragment.*
import ru.endroad.sample.jarmark.R
import ru.endroad.sample.jarmark.application.BaseFragment
import ru.endroad.sample.jarmark.application.withArguments

class PanoramicPlaceFragment : BaseFragment() {

	companion object {

		private const val PANORAMA_NAME = "PANORAMA_NAME"

		fun create(panoramaName: String): Fragment =
			PanoramicPlaceFragment().withArguments(
				PANORAMA_NAME to panoramaName,
			)
	}

	override val layout = R.layout.panorama360_viewer_fragment

	private val panorama: Panorama
		get() = requireArguments()
			.getString(PANORAMA_NAME)
			.let { name -> PanoramaDataSource(requireContext()).getList().find { panorama -> panorama.name == name } }
			.let(::requireNotNull)

	override fun setupViewComponents() {
		setToolbarText(panorama.name)
		val cubeTexture = panorama.texture.toCubeTexture(requireContext())

		panorama_viewer.bindLifecycle(this)
		panorama_viewer.texture = cubeTexture
	}
}