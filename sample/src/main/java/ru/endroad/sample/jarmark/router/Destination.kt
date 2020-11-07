package ru.endroad.sample.jarmark.router

import android.content.Context
import android.content.Intent

interface Destination

interface ActivityDestination : Destination {

	fun createIntent(context: Context): Intent
}