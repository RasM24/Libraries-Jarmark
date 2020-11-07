package ru.endroad.sample.jarmark.application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View =
	LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

inline fun <reified F : Fragment> F.withArguments(vararg pairs: Pair<String, Any?>): F {
	arguments = bundleOf(*pairs)
	return this
}