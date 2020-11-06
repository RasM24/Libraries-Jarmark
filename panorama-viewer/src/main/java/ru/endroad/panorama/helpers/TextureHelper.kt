package ru.endroad.panorama.helpers

import android.graphics.Bitmap
import android.opengl.GLES20
import android.opengl.GLUtils

internal fun Bitmap?.toTexture(): Int {
	this ?: return 0

	val textureHandle = IntArray(1)
	GLES20.glGenTextures(1, textureHandle, 0)
	if (textureHandle[0] != 0) {
		// Bind to the texture in OpenGL
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0])
		// Set filtering
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST)
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST)
		// Load the bitmap into the bound texture.
		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, this, 0)
		// Recycle the bitmap, since its data has been loaded into OpenGL.
		this.recycle()
	}
	if (textureHandle[0] == 0) {
		throw RuntimeException("Error loading texture.")
	}
	return textureHandle[0]
}