package com.ahr.usergithub.util

import android.content.Intent
import androidx.fragment.app.Fragment
import com.ahr.usergithub.R
import com.ahr.usergithub.data.User

fun Fragment.shareUser(user: User) {
    val sendIntent = Intent()
        .apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, user.url)
            type = "text/plain"
        }
    val shareIntent = Intent.createChooser(sendIntent, getString(R.string.share_user_title))
    startActivity(shareIntent)
}