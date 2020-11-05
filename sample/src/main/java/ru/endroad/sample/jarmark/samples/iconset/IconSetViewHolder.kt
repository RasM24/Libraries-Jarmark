package ru.endroad.sample.jarmark.samples.iconset

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.iconset_item.view.*
import ru.endroad.sample.jarmark.R
import ru.endroad.sample.jarmark.application.inflate

class IconSetViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.iconset_item)) {

	fun bind(item: IconSet) {
		itemView.bind(item)
	}

	private fun View.bind(item: IconSet) {
		name.text = item.name
		icon_alternative.setImageResource(item.iconAlternativeId)
		icon_filled.setImageResource(item.iconFilledId)
		icon_outlined.setImageResource(item.iconOutlinedId)
	}

	private fun ImageView.setImageResource(@DrawableRes id: Int?) {
		id?.let(::setImageResource) ?: setImageDrawable(null)
	}
}