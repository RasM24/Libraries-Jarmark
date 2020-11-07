package ru.endroad.panorama.render

import android.opengl.GLSurfaceView

interface PanoramaRender : GLSurfaceView.Renderer {
	fun onTurnVision(deltaFi: Float, deltaPsy: Float)
	fun onChangeZoom(zoom: Float)
}