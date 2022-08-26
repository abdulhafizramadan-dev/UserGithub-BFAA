package com.ahr.usergithub.util

import androidx.annotation.RawRes
import com.ahr.usergithub.R

sealed class LottieViewType(@RawRes val rawRes: Int) {
    object Loading : LottieViewType(R.raw.lottie_loading)
    object Error : LottieViewType(R.raw.lottie_error)
}