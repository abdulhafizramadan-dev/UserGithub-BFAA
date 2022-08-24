package com.ahr.usergithub

object LibsVersion {
    object AndroidX {
        const val coreKtx = "1.8.0"
        const val appcompat = "1.5.0"
        const val splashscreen = "1.0.0"
        const val constraintlayout = "2.1.4"
    }
    object Google {
        const val material = "1.6.1"
    }
    const val glide = "4.11.0"
    const val circleimageview = "3.1.0"
}

object TestLibsVersion {
    object Junit {
        const val junit = "4.13.2"
        const val extJunit = "1.1.3"
    }
    object Espresso {
        const val espresso_core = "3.4.0"
    }
}

object Libs {
    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${LibsVersion.AndroidX.coreKtx}"
        const val appcompat = "androidx.appcompat:appcompat:${LibsVersion.AndroidX.appcompat}"
        const val splashscreen = "androidx.core:core-splashscreen:${LibsVersion.AndroidX.splashscreen}"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:${LibsVersion.AndroidX.constraintlayout}"
    }
    object Google {
        const val material = "com.google.android.material:material:${LibsVersion.Google.material}"
    }
    const val glide = "com.github.bumptech.glide:glide:${LibsVersion.glide}"
    const val circleimageview = "de.hdodenhof:circleimageview:${LibsVersion.circleimageview}"
}

object TestLibs {
    object Junit {
        const val junit = "junit:junit:${TestLibsVersion.Junit.junit}"
        const val extJunit = "androidx.test.ext:junit:${TestLibsVersion.Junit.extJunit}"
    }
    object Espresso {
        const val espresso_core = "androidx.test.espresso:espresso-core:${TestLibsVersion.Espresso.espresso_core}"
    }
}