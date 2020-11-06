package ru.endroad.sample.jarmark.samples.textappearance

import ru.endroad.sample.jarmark.R
import ru.endroad.sample.jarmark.application.BaseFragment

class TextAppearanceFragment : BaseFragment() {

	override val layout = R.layout.text_appearance_fragment

	override fun setupViewComponents() {
		setToolbarText(getString(R.string.appearance_title))
	}
}