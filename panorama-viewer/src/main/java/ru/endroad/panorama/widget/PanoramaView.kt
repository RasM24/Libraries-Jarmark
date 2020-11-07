package ru.endroad.panorama.widget

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import ru.endroad.panorama.render.cube.CubeTexture
import ru.endroad.panorama.render.cube.PanoramaTexture
import ru.endroad.panorama.render.cube.CubeRender
import kotlin.math.hypot

class PanoramaView constructor(context: Context?, attrs: AttributeSet?) : GLSurfaceView(context, attrs), LifecycleObserver {

	private var xAxisVision = 0f
	private var yAxisVision = 0f
	private var zoom = 0f

	private var forZoom = 0f
	private lateinit var render: CubeRender

	var texture: PanoramaTexture? = null
		set(value) {
			field = value

			render = CubeRender(context, value as CubeTexture)
			super.setRenderer(render)
		}

	fun bindLifecycle(lifecycleOwner: LifecycleOwner) {
		lifecycleOwner.lifecycle.addObserver(this)
		setEGLContextClientVersion(2)
	}

	override fun onTouchEvent(event: MotionEvent): Boolean {
		if (event.action == MotionEvent.ACTION_DOWN) {
			onTouchDown(event)
		}
		when (event.actionMasked) {
			MotionEvent.ACTION_POINTER_DOWN -> onTouchPointerDown(event)
			MotionEvent.ACTION_POINTER_UP   -> onTouchPointerUp(event)
			MotionEvent.ACTION_MOVE         -> onTouchPointerMove(event)
		}
		return true
	}

	private fun onTouchDown(event: MotionEvent) {
		xAxisVision = event.x
		yAxisVision = event.y
	}

	private fun onTouchPointerDown(event: MotionEvent) {
		xAxisVision = (event.getX(FIRST_FINGER) + event.getX(SECOND_FINGER)) / 2
		yAxisVision = (event.getY(FIRST_FINGER) + event.getY(SECOND_FINGER)) / 2
		forZoom = hypot(
			event.getX(FIRST_FINGER) - event.getX(SECOND_FINGER).toDouble(),
			event.getY(FIRST_FINGER) - event.getY(SECOND_FINGER).toDouble()
		).toFloat()
		zoom = render.zoom
	}

	private fun onTouchPointerUp(event: MotionEvent) {
		if (event.actionIndex == SECOND_FINGER) {
			xAxisVision = event.getX(FIRST_FINGER)
			yAxisVision = event.getY(FIRST_FINGER)
		}
		if (event.actionIndex == FIRST_FINGER) {
			xAxisVision = event.getX(SECOND_FINGER)
			yAxisVision = event.getY(SECOND_FINGER)
		}
	}

	private fun onTouchPointerMove(event: MotionEvent) {
		val xx: Float
		val yy: Float
		if (event.pointerCount == 1) {
			xx = event.x
			yy = event.y
		} else {
			xx = (event.getX(FIRST_FINGER) + event.getX(SECOND_FINGER)) / 2
			yy = (event.getY(FIRST_FINGER) + event.getY(SECOND_FINGER)) / 2
			var bb = zoom * forZoom / hypot(
				event.getX(FIRST_FINGER) - event.getX(
					SECOND_FINGER
				).toDouble(),
				event.getY(FIRST_FINGER) - event.getY(
					SECOND_FINGER
				).toDouble()
			).toFloat()
			if (bb > 10) bb = 10f
			if (bb < 2) bb = 2f
			render.onChangeZoom(bb)
		}
		render.run { onTurnVision(-(xx - xAxisVision) * zoom / 3000, (yy - yAxisVision) * zoom / 3000) }
		xAxisVision = xx
		yAxisVision = yy
	}

	@OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
	private fun runRender() = onResume()

	@OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
	private fun stopRender() = onPause()

	companion object {
		const val FIRST_FINGER = 0
		const val SECOND_FINGER = 1
	}
}