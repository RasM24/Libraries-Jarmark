package ru.endroad.sample.jarmark.samples.panorama

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import ru.endroad.panorama.render.cube.CubeTexture

data class Panorama(val name: String, val texture: TexturePaths)

class TexturePaths(val top: String, val bottom: String, val left: String, val right: String, val front: String, val back: String)

fun TexturePaths.toCubeTexture(context: Context) =
	CubeTexture(
		topBitmap = context.loadBitmap(top),
		bottomBitmap = context.loadBitmap(bottom),
		leftBitmap = context.loadBitmap(left),
		rightBitmap = context.loadBitmap(right),
		frontBitmap = context.loadBitmap(front),
		backBitmap = context.loadBitmap(back)
	)

private fun Context.loadBitmap(path: String): Bitmap? =
	runCatching { assets.open(path).use(BitmapFactory::decodeStream) }.getOrNull()