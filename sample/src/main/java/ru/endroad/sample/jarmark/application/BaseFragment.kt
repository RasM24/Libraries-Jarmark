package ru.endroad.sample.jarmark.application

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

	abstract val layout: Int

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
		inflater.inflate(layout, container, false)

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		setupViewComponents()
	}

	protected open fun setupViewComponents() = Unit

	protected fun setToolbarText(text: CharSequence) {
		activity?.title = text
	}
}