package ru.endroad.sample.jarmark.samples.iconset

import ru.endroad.sample.jarmark.R

class IconDataSet {

	private val hardcoreData = listOf(

		//Account
		IconSet("ic_account_box", R.drawable.ic_account_box, R.drawable.ic_account_box_outline),
		IconSet("ic_account_circle", R.drawable.ic_account_circle, R.drawable.ic_account_circle_outline),
		IconSet("ic_verified_user", R.drawable.ic_verified_user, R.drawable.ic_verified_user_outline),

		//Action
		IconSet("ic_create", R.drawable.ic_create, R.drawable.ic_create_outline),
		IconSet("ic_delete", R.drawable.ic_delete_forever, R.drawable.ic_delete_forever_outline),
		IconSet("ic_add", R.drawable.ic_add_circle, R.drawable.ic_add_circle_outline, R.drawable.ic_add),
		IconSet("ic_check_circle", R.drawable.ic_check_circle, R.drawable.ic_check_circle_outline, R.drawable.ic_check),
		IconSet("ic_clear", R.drawable.ic_clear, R.drawable.ic_block),
		IconSet("ic_backspace", R.drawable.ic_backspace, R.drawable.ic_backspace_outline),

		//Informer
		IconSet("ic_error", R.drawable.ic_error, R.drawable.ic_error_outline),
		IconSet("ic_info", R.drawable.ic_info, R.drawable.ic_info_outline),

		//User Social Action
		IconSet("ic_send", R.drawable.ic_send, R.drawable.ic_send_outline),
		IconSet("ic_chat", R.drawable.ic_chat, R.drawable.ic_chat_outline),
		IconSet("ic_comment", R.drawable.ic_comment, R.drawable.ic_comment_outline),
		IconSet("ic_thumb_down", R.drawable.ic_thumb_down, R.drawable.ic_thumb_down_outline),
		IconSet("ic_thumb_up", R.drawable.ic_thumb_up, R.drawable.ic_thumb_up_outline),
		IconSet("ic_favorite", R.drawable.ic_favorite, R.drawable.ic_favorite_outline),
		IconSet("ic_grade", R.drawable.ic_grade, R.drawable.ic_grade_outline),

		//other
		IconSet("ic_label", R.drawable.ic_label, R.drawable.ic_label_outline),
		IconSet("ic_calendar", R.drawable.ic_calendar_today, R.drawable.ic_calendar_today_outline),
		IconSet("ic_email", R.drawable.ic_email, R.drawable.ic_email_outline),
		IconSet("ic_home", R.drawable.ic_home, R.drawable.ic_home_outline),
		IconSet("ic_https", R.drawable.ic_https, R.drawable.ic_https_outline),
		IconSet("ic_note_add", R.drawable.ic_note_add, R.drawable.ic_note_add_outline),
		IconSet("ic_pause", R.drawable.ic_pause_circle_filled, R.drawable.ic_pause_circle_outline, R.drawable.ic_pause),
		IconSet("ic_play", R.drawable.ic_play_circle_filled, R.drawable.ic_play_circle_outline, R.drawable.ic_play_arrow),
		IconSet("ic_location", R.drawable.ic_location, R.drawable.ic_location_outline),
		IconSet("ic_sd_storage", R.drawable.ic_sd_storage, R.drawable.ic_sd_storage_outline),
		IconSet("ic_shop", R.drawable.ic_shop, R.drawable.ic_shop_outline),
		IconSet("ic_sd_storage", R.drawable.ic_open_new, R.drawable.ic_open_browser),
	)

	fun get(): List<IconSet> = hardcoreData
}