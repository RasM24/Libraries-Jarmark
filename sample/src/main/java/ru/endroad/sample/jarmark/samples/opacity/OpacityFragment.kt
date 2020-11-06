package ru.endroad.sample.jarmark.samples.opacity

import kotlinx.android.synthetic.main.opacity_background_change_layout.*
import kotlinx.android.synthetic.main.opacity_fragment.root
import ru.endroad.sample.jarmark.R
import ru.endroad.sample.jarmark.application.BaseFragment

class OpacityFragment : BaseFragment() {

	override val layout = R.layout.opacity_fragment

	override fun setupViewComponents() {
		setToolbarText(getString(R.string.opacity_title))

		background_white.setOnClickListener { setBackgroundColor(R.color.background_white) }
		background_gray.setOnClickListener { setBackgroundColor(R.color.background_gray) }
		background_red.setOnClickListener { setBackgroundColor(R.color.background_red) }
		background_green.setOnClickListener { setBackgroundColor(R.color.background_green) }
		background_blue.setOnClickListener { setBackgroundColor(R.color.background_blue) }
	}

	private fun setBackgroundColor(resId: Int) {
		root.setBackgroundColor(resources.getColor(resId))
	}
}