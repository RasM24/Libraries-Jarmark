package ru.endroad.sample.jarmark.samples.resourcespack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.endroad.sample.jarmark.R

class ThemeActivity : AppCompatActivity() {

	companion object {

		private const val THEME_KEY = "THEME_KEY"

		fun createIntent(context: Context, themeRes: Int) =
			Intent(context, ThemeActivity::class.java)
				.apply { putExtra(THEME_KEY, themeRes) }
	}

	private val themeRes: Int
		get() = intent.getIntExtra(THEME_KEY, 0)

	val layout = R.layout.theme_activity

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setTheme(themeRes)
		setContentView(layout)
	}
}