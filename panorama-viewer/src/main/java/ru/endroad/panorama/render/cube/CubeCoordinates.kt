package ru.endroad.panorama.render.cube

private const val ONE = 1.0f
private const val ZERO = 0.0f
internal const val POSITION_DATA_SIZE = 3
internal const val TEXTURE_COORDINATE_DATA_SIZE = 2
internal const val BYTES_PER_FLOAT = 4
internal const val COUNT_TRIANGLES_POINT = 6

// X, Y, Z
internal val cubePositionData = floatArrayOf(
	// Front face
	-ONE, ONE, ONE, ONE, ONE, ONE, -ONE, -ONE, ONE, -ONE, -ONE, ONE, ONE, ONE, ONE, ONE, -ONE, ONE,
	// Left face
	-ONE, ONE, -ONE, -ONE, ONE, ONE, -ONE, -ONE, -ONE, -ONE, -ONE, -ONE, -ONE, ONE, ONE, -ONE, -ONE, ONE,
	// Back face
	ONE, ONE, -ONE, -ONE, ONE, -ONE, ONE, -ONE, -ONE, ONE, -ONE, -ONE, -ONE, ONE, -ONE, -ONE, -ONE, -ONE,
	// Right face
	ONE, ONE, ONE, ONE, ONE, -ONE, ONE, -ONE, ONE, ONE, -ONE, ONE, ONE, ONE, -ONE, ONE, -ONE, -ONE,
	// Top face
	-ONE, ONE, -ONE, ONE, ONE, -ONE, -ONE, ONE, ONE, -ONE, ONE, ONE, ONE, ONE, -ONE, ONE, ONE, ONE,
	// Bottom face
	-ONE, -ONE, ONE, ONE, -ONE, ONE, -ONE, -ONE, -ONE, -ONE, -ONE, -ONE, ONE, -ONE, ONE, ONE, -ONE, -ONE
)

// S, T (or X, Y)
internal val cubeTextureCoordinateData = floatArrayOf(
	ONE, ZERO, ZERO, ZERO, ONE, ONE, ONE, ONE, ZERO, ZERO, ZERO, ONE, // Front face
	ONE, ZERO, ZERO, ZERO, ONE, ONE, ONE, ONE, ZERO, ZERO, ZERO, ONE, // Right face
	ONE, ZERO, ZERO, ZERO, ONE, ONE, ONE, ONE, ZERO, ZERO, ZERO, ONE, // Back face
	ONE, ZERO, ZERO, ZERO, ONE, ONE, ONE, ONE, ZERO, ZERO, ZERO, ONE, // Left face
	ONE, ZERO, ZERO, ZERO, ONE, ONE, ONE, ONE, ZERO, ZERO, ZERO, ONE, // Top face
	ONE, ZERO, ZERO, ZERO, ONE, ONE, ONE, ONE, ZERO, ZERO, ZERO, ONE // Bottom face
)