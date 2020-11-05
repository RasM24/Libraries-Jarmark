package ru.endroad.sample.jarmark.router

import org.koin.dsl.module
import org.koin.experimental.builder.single
import ru.endroad.sample.jarmark.R

val moduleNavigation = module {
	single { Navigator(R.id.root) }

	single<HubRouter>()
}