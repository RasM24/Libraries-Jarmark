package ru.endroad.sample.jarmark.samples.panorama

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.panorama360_item.view.*
import ru.endroad.sample.jarmark.R
import ru.endroad.sample.jarmark.application.inflate

class PlaceViewHolder(
	private val onPanoramaClick: (Panorama) -> Unit,
	parent: ViewGroup
) : RecyclerView.ViewHolder(parent.inflate(R.layout.panorama360_item)) {

	private companion object {

		const val ASSET_DIRECTORY = "file:///android_asset/"
	}

	fun bind(item: Panorama) {
		itemView.bind(item)
		itemView.setOnClickListener { onPanoramaClick(item) }
	}

	private fun View.bind(item: Panorama) {
		name.text = item.name
		preview.load("$ASSET_DIRECTORY${item.texture.front}")
	}

	private fun ImageView.load(path: String) {
		Picasso.get().load(path).into(preview)
	}
}