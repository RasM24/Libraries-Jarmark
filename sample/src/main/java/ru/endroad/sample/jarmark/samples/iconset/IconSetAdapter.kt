package ru.endroad.sample.jarmark.samples.iconset

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class IconSetAdapter : RecyclerView.Adapter<IconSetViewHolder>() {

	private companion object {

		private const val ITEM_VIEW_TYPE = 1
	}

	var items: List<IconSet> = listOf()
		set(value) {
			field = value
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconSetViewHolder =
		IconSetViewHolder(parent)

	override fun onBindViewHolder(holder: IconSetViewHolder, position: Int) {
		holder.bind(items[position])
	}

	override fun getItemViewType(position: Int): Int = ITEM_VIEW_TYPE

	override fun getItemCount(): Int = items.size
}