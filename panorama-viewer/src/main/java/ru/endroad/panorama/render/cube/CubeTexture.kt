package ru.endroad.panorama.render.cube

import android.graphics.Bitmap
import android.opengl.GLES20
import ru.endroad.panorama.helpers.toTexture

sealed class PanoramaTexture

class CubeTexture(
	private val topBitmap: Bitmap?,
	private val bottomBitmap: Bitmap?,
	private val leftBitmap: Bitmap?,
	private val rightBitmap: Bitmap?,
	private val frontBitmap: Bitmap?,
	private val backBitmap: Bitmap?
) : PanoramaTexture() {

	var topTextureReference: Int = 0
	var bottomTextureReference: Int = 0
	var leftTextureReference: Int = 0
	var rightTextureReference: Int = 0
	var frontTextureReference: Int = 0
	var backTextureReference: Int = 0

	fun init() {
		topTextureReference = topBitmap.toTexture()
		bottomTextureReference = bottomBitmap.toTexture()
		rightTextureReference = rightBitmap.toTexture()
		leftTextureReference = leftBitmap.toTexture()
		frontTextureReference = frontBitmap.toTexture()
		backTextureReference = backBitmap.toTexture()
		GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D)
	}
}