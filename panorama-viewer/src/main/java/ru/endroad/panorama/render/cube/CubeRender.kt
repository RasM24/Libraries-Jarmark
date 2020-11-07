package ru.endroad.panorama.render.cube

import android.content.Context
import android.opengl.GLES20
import android.opengl.Matrix
import ru.endroad.jarmark.panorama.R
import ru.endroad.panorama.helpers.createAndLinkProgram
import ru.endroad.panorama.helpers.loadShaders
import ru.endroad.panorama.render.PanoramaRender
import java.nio.ByteBuffer
import java.nio.ByteOrder
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import kotlin.math.cos
import kotlin.math.sin

internal class CubeRender internal constructor(
	private val mActivityContext: Context,
	private val cubeTextures: CubeTexture
) : PanoramaRender {

	private val mCubePositions = ByteBuffer.allocateDirect(cubePositionData.size * BYTES_PER_FLOAT)
		.order(ByteOrder.nativeOrder())
		.asFloatBuffer()
		.apply { put(cubePositionData).position(0) }

	private val mCubeTextureCoordinates = ByteBuffer.allocateDirect(cubeTextureCoordinateData.size * BYTES_PER_FLOAT)
		.order(ByteOrder.nativeOrder())
		.asFloatBuffer()
		.apply { put(cubeTextureCoordinateData).position(0) }

	private val mAccumulatedRotation = FloatArray(16)
	private val mCurrentRotation = FloatArray(16)

	var zoom = 10f
		set(value) {
			Matrix.frustumM(mProjectionMatrix, 0, -ratio / 10, ratio / 10, -0.1f, 0.1f, 1f / value, 5.0f)
			field = value
		}

	private val mModelMatrix = FloatArray(16)
	private val mViewMatrix = FloatArray(16)
	private val mProjectionMatrix = FloatArray(16)
	private val mMVPMatrix = FloatArray(16)
	private val mTemporaryMatrix = FloatArray(16)
	private var mMVPMatrixHandle = 0
	private var mMVMatrixHandle = 0
	private var mPositionHandle = 0
	private var mProgramHandle = 0

	private var visionFi = 0f // азимутальный угол камеры
	private var visionPsy = 0f // зенитный угол камеры
		set(value) {
			field = when {
				value > 1.5f  -> 1.5f
				value < -1.5f -> -1.5f
				else          -> value
			}
		}
	private var ratio = 0f

	override fun onSurfaceCreated(glUnused: GL10, config: EGLConfig) { // Set the background clear color to black.
		GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f)

		GLES20.glEnable(GLES20.GL_CULL_FACE)
		GLES20.glEnable(GLES20.GL_DEPTH_TEST)

		Matrix.setLookAtM(mViewMatrix, 0, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f)
		val (vertexShaderHandle, fragmentShaderHandle) = mActivityContext.loadShaders(R.raw.cube_vertex_shader, R.raw.cube_fragment_shader)
		mProgramHandle = createAndLinkProgram(vertexShaderHandle, fragmentShaderHandle)

		cubeTextures.init()
		Matrix.setIdentityM(mAccumulatedRotation, 0)
		onDrawFrame(glUnused)
	}

	override fun onSurfaceChanged(glUnused: GL10, width: Int, height: Int) { // Set the OpenGL viewport to the same size as the surface.
		GLES20.glViewport(0, 0, width, height)
		val ratio = width.toFloat() / height
		this.ratio = ratio
		Matrix.frustumM(mProjectionMatrix, 0, -ratio / 10, ratio / 10, -0.1f, 0.1f, 1f / zoom, 5.0f)
	}

	override fun onDrawFrame(glUnused: GL10) {
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT or GLES20.GL_DEPTH_BUFFER_BIT)

		Matrix.setLookAtM(mViewMatrix, 0, 0f, 0f, 0f, sin(visionFi) * cos(visionPsy), sin(visionPsy), -(cos(visionPsy) * cos(visionFi)), 0f, 1f / zoom, 0f)

		GLES20.glUseProgram(mProgramHandle)
		mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgramHandle, "u_MVPMatrix")
		mMVMatrixHandle = GLES20.glGetUniformLocation(mProgramHandle, "u_MVMatrix")
		val mTextureUniformHandle = GLES20.glGetUniformLocation(mProgramHandle, "u_Texture")
		mPositionHandle = GLES20.glGetAttribLocation(mProgramHandle, "a_Position")
		val mTextureCoordinateHandle = GLES20.glGetAttribLocation(mProgramHandle, "a_TexCoordinate")
		Matrix.setIdentityM(mModelMatrix, 0)
		Matrix.setIdentityM(mCurrentRotation, 0)
		Matrix.multiplyMM(mTemporaryMatrix, 0, mCurrentRotation, 0, mAccumulatedRotation, 0)
		System.arraycopy(mTemporaryMatrix, 0, mAccumulatedRotation, 0, 16)
		Matrix.multiplyMM(mTemporaryMatrix, 0, mModelMatrix, 0, mAccumulatedRotation, 0)
		System.arraycopy(mTemporaryMatrix, 0, mModelMatrix, 0, 16)
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0)
		GLES20.glUniform1i(mTextureUniformHandle, 0)
		mCubeTextureCoordinates.position(0)
		GLES20.glVertexAttribPointer(mTextureCoordinateHandle, TEXTURE_COORDINATE_DATA_SIZE, GLES20.GL_FLOAT, false, 0, mCubeTextureCoordinates)
		GLES20.glEnableVertexAttribArray(mTextureCoordinateHandle)
		passInThePositionInformation()
		drawCube()
	}

	private fun drawCube() {
		Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0)
		GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mMVPMatrix, 0)
		Matrix.multiplyMM(mTemporaryMatrix, 0, mProjectionMatrix, 0, mMVPMatrix, 0)
		System.arraycopy(mTemporaryMatrix, 0, mMVPMatrix, 0, 16)
		GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0)

		listOf(
			cubeTextures.frontTextureReference,
			cubeTextures.rightTextureReference,
			cubeTextures.backTextureReference,
			cubeTextures.leftTextureReference,
			cubeTextures.topTextureReference,
			cubeTextures.bottomTextureReference,
		).forEachIndexed { index, textureReference ->
			GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureReference)
			GLES20.glDrawArrays(GLES20.GL_TRIANGLES, index * COUNT_TRIANGLES_POINT, COUNT_TRIANGLES_POINT)
		}
	}

	private fun passInThePositionInformation() {
		mCubePositions.position(0)
		GLES20.glVertexAttribPointer(mPositionHandle, POSITION_DATA_SIZE, GLES20.GL_FLOAT, false, 0, mCubePositions)
		GLES20.glEnableVertexAttribArray(mPositionHandle)
	}

	override fun onTurnVision(deltaFi: Float, deltaPsy: Float) {
		visionFi += deltaFi
		visionPsy += deltaPsy
	}

	override fun onChangeZoom(zoom: Float) {
		this.zoom = zoom
	}
}
