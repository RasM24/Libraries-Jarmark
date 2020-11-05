package ru.endroad.sample.jarmark.router

import org.koin.dsl.module
import ru.endroad.sample.jarmark.R

val moduleNavigation = module {
	single { Navigator(R.id.root) }
}