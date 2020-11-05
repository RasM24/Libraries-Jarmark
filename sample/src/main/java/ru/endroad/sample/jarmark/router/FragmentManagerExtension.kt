package ru.endroad.sample.jarmark.router

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.endroad.sample.jarmark.R

private val Fragment.key: String? get() = this::class.simpleName

fun FragmentManager.replace(fragment: Fragment, @IdRes container: Int) {
	beginTransaction().run {
		setSlideAnimationTransaction()
		replace(container, fragment)
		if (backStackEntryCount != 0) {
			popBackStack()
			addToBackStack(fragment.key)
		}
		commitAllowingStateLoss()
	}
}

fun FragmentManager.open(fragment: Fragment, @IdRes container: Int) {
	findFragmentById(container)?.onHiddenChanged(true)
	beginTransaction().run {
		setSlideAnimationTransaction()
		replace(container, fragment)
		addToBackStack(fragment.key)
		commitAllowingStateLoss()
	}
}

private fun FragmentTransaction.setSlideAnimationTransaction() {
	setCustomAnimations(
		R.anim.default_slide_in_right,
		R.anim.default_slide_out_left,
		R.anim.default_slide_in_left,
		R.anim.default_slide_out_right
	)
}