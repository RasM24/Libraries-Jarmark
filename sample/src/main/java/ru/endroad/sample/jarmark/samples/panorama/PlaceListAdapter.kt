package ru.endroad.sample.jarmark.samples.panorama

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class PlaceListAdapter(
	private val onPanoramaClick: (Panorama) -> Unit
) : RecyclerView.Adapter<PlaceViewHolder>() {

	private companion object {

		const val DEFAULT_ITEM_VIEW_TYPE = 1
	}

	var items: List<Panorama> = listOf()
		set(value) {
			field = value
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder =
		PlaceViewHolder(onPanoramaClick, parent)

	override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
		holder.bind(items[position])
	}

	override fun getItemViewType(position: Int): Int =
		DEFAULT_ITEM_VIEW_TYPE

	override fun getItemCount(): Int =
		items.size
}