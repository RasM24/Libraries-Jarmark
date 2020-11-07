package ru.endroad.panorama.helpers

import android.content.Context
import androidx.annotation.RawRes
import java.io.BufferedReader
import java.io.InputStreamReader

internal fun Context.readTextFileFromRawResource(@RawRes resourceId: Int): String? =
	runCatching {
		resources.openRawResource(resourceId)
			.run(::InputStreamReader)
			.run(::BufferedReader)
			.readText()
	}.getOrNull()

private fun BufferedReader.readText() =
	StringBuilder().apply { readLines().forEach { line -> appendln(line) } }.toString()