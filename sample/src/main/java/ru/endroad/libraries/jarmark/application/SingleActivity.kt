package ru.endroad.libraries.jarmark.application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.endroad.libraries.jarmark.R

class SingleActivity : AppCompatActivity() {

	private val layout = R.layout.root_activity
	private val theme = R.style.ThemeSketchpad

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setTheme(theme)
		setContentView(layout)
	}
}