package ru.endroad.sample.jarmark.samples.iconset

import androidx.annotation.DrawableRes

data class IconSet(
	val name: String,
	@DrawableRes val iconFilledId: Int,
	@DrawableRes val iconOutlinedId: Int,
	@DrawableRes val iconAlternativeId: Int? = null,
)