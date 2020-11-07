package ru.endroad.sample.jarmark.application

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.root_activity.*
import org.koin.android.ext.android.inject
import ru.endroad.sample.jarmark.R
import ru.endroad.sample.jarmark.router.Navigator

class SingleActivity : AppCompatActivity() {

	private val layout = R.layout.root_activity
	private val theme = R.style.ThemeEndroad_Libraries

	private val navigator: Navigator by inject()

	override fun onCreate(savedInstanceState: Bundle?) {
		navigator.hubActivity = this
		super.onCreate(savedInstanceState)
		setTheme(theme)
		setContentView(layout)
		setupToolbar()

		savedInstanceState ?: openInitiateFragment()
	}

	override fun onDestroy() {
		navigator.hubActivity = null
		super.onDestroy()
	}

	private fun setupToolbar() {
		setSupportActionBar(toolbar)
		toolbar.setNavigationOnClickListener { onBackPressed() }
		supportFragmentManager.addOnBackStackChangedListener {
			supportActionBar?.setHomeEnabled()
		}
		supportActionBar?.setHomeEnabled()
	}

	private fun ActionBar.setHomeEnabled() {
		this.setDisplayHomeAsUpEnabled(supportFragmentManager.backStackEntryCount != 0)
	}

	private fun openInitiateFragment() {
		navigator.replace(HubFragment())
	}
}