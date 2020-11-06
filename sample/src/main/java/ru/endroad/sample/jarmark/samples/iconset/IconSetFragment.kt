package ru.endroad.sample.jarmark.samples.iconset

import kotlinx.android.synthetic.main.iconset_listing_fragment.*
import ru.endroad.sample.jarmark.R
import ru.endroad.sample.jarmark.application.BaseFragment

class IconSetFragment : BaseFragment() {

	override val layout = R.layout.iconset_listing_fragment

	private val adapter = IconSetAdapter()
	private val dataSet = IconDataSet()

	override fun setupViewComponents() {
		setToolbarText(getString(R.string.set_icon_title))
		list.adapter = adapter
		adapter.items = dataSet.get()
	}
}
