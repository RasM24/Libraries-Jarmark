package ru.endroad.panorama.helpers

import android.content.Context
import android.opengl.GLES20
import androidx.annotation.RawRes

private const val SHADER_POSITION_ATTRIBUTE = "a_Position"
private const val SHADER_TEXTURE_COORDINATE_ATTRIBUTE = "a_TexCoordinate"

internal fun Context.loadShaders(@RawRes vertexShaderId: Int, @RawRes fragmentShaderId: Int): Pair<Int, Int> {
	val vertexShader = readTextFileFromRawResource(vertexShaderId)
	val fragmentShader = readTextFileFromRawResource(fragmentShaderId)
	return compileShader(GLES20.GL_VERTEX_SHADER, vertexShader) to compileShader(GLES20.GL_FRAGMENT_SHADER, fragmentShader)
}

/**
 * Helper function to compile a shader.
 *
 * @param shaderType   The shader type.
 * @param shaderSource The shader source code.
 * @return An OpenGL handle to the shader.
 */
internal fun compileShader(shaderType: Int, shaderSource: String?): Int {
	val shaderHandle = GLES20.glCreateShader(shaderType)

	require(shaderHandle != 0) { "Error creating shader." }

	return shaderHandle.apply {
		GLES20.glShaderSource(this, shaderSource) // Pass in the shader source.
		GLES20.glCompileShader(this) // Compile the shader.

		val compileStatus = IntArray(1)// Get the compilation status.
		GLES20.glGetShaderiv(this, GLES20.GL_COMPILE_STATUS, compileStatus, 0)

		if (compileStatus[0] == 0) { // If the compilation failed, delete the shader.
			GLES20.glDeleteShader(this)
			throw RuntimeException("Error creating shader.")
		}
	}
}

/**
 * Helper function to compile and link a program.
 *
 * @param vertexShaderHandle   An OpenGL handle to an already-compiled vertex shader.
 * @param fragmentShaderHandle An OpenGL handle to an already-compiled fragment shader.
 * @return An OpenGL handle to the program.
 */
internal fun createAndLinkProgram(vertexShaderHandle: Int, fragmentShaderHandle: Int): Int {
	val programHandle = GLES20.glCreateProgram()
	val attributes = arrayOf(SHADER_POSITION_ATTRIBUTE, SHADER_TEXTURE_COORDINATE_ATTRIBUTE)

	require(programHandle != 0) { "Error creating program." }

	return programHandle.apply {
		GLES20.glAttachShader(this, vertexShaderHandle) // Bind the vertex shader to the program.
		GLES20.glAttachShader(this, fragmentShaderHandle) // Bind the fragment shader to the program.
		attributes.forEachIndexed { i, attribute -> // Bind attributes
			GLES20.glBindAttribLocation(this, i, attribute)
		}

		GLES20.glLinkProgram(this) // Link the two shaders together into a program.

		val linkStatus = IntArray(1) // Get the link status.
		GLES20.glGetProgramiv(this, GLES20.GL_LINK_STATUS, linkStatus, 0)

		if (linkStatus[0] == 0) { // If the link failed, delete the program.
			GLES20.glDeleteProgram(this)
			throw RuntimeException("Error creating program.")
		}
	}
}
