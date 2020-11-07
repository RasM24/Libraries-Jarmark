package ru.endroad.sample.jarmark.samples.panorama

import android.content.Context
import android.util.Log

class PanoramaDataSource(
	private val context: Context,
) {

	companion object {

		const val POINT_DIRECTORY_PATH = "place"

		const val TOP_TEXTURE_PATH = "top.jpg"
		const val BOTTOM_TEXTURE_PATH = "bottom.jpg"
		const val RIGHT_TEXTURE_PATH = "right.jpg"
		const val LEFT_TEXTURE_PATH = "left.jpg"
		const val FRONT_TEXTURE_PATH = "front.jpg"
		const val BACK_TEXTURE_PATH = "back.jpg"
	}

	private val placesDirectory = context.assets
		.list(POINT_DIRECTORY_PATH).takeUnless { it.isNullOrEmpty() }
		.let(::requireNotNull)

	private val data = placesDirectory.mapNotNull(::loadPanorama)

	private fun loadPanorama(path: String): Panorama? =
		runCatching {
			val texturePaths = TexturePaths(
				top = "$POINT_DIRECTORY_PATH/$path/$TOP_TEXTURE_PATH",
				bottom = "$POINT_DIRECTORY_PATH/$path/$BOTTOM_TEXTURE_PATH",
				right = "$POINT_DIRECTORY_PATH/$path/$RIGHT_TEXTURE_PATH",
				left = "$POINT_DIRECTORY_PATH/$path/$LEFT_TEXTURE_PATH",
				front = "$POINT_DIRECTORY_PATH/$path/$FRONT_TEXTURE_PATH",
				back = "$POINT_DIRECTORY_PATH/$path/$BACK_TEXTURE_PATH"
			)

			Panorama(path, texturePaths)
		}.onFailure { Log.e("Panorama", "Check Textures Exception: ${it.localizedMessage}") }
			.getOrNull()

	fun getList(): List<Panorama> = data
}