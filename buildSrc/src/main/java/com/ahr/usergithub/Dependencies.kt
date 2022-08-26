package com.ahr.usergithub

private object LibsVersion {
    object AndroidX {
        const val coreKtx = "1.8.0"
        const val appcompat = "1.5.0"
        const val splashscreen = "1.0.0"
        const val constraintlayout = "2.1.4"
        const val navigationComponent = "2.5.1"
    }
    object Ktx {
        const val livedataKtx = "2.6.0-alpha01"
    }
    object Google {
        const val material = "1.6.1"
    }
    object Square {
        const val gson = "2.9.0"
        const val retrofit = "2.9.0"
        const val loggingInterceptor = "4.10.0"
    }
    const val coroutines = "1.6.4"
    const val glide = "4.11.0"
    const val circleimageview = "3.1.0"
    const val networkResponseAdapter = "5.0.0"
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
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${LibsVersion.AndroidX.navigationComponent}"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${LibsVersion.AndroidX.navigationComponent}"
    }
    object Ktx {
        const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${LibsVersion.Ktx.livedataKtx}"
    }
    object Google {
        const val material = "com.google.android.material:material:${LibsVersion.Google.material}"
    }
    object Square {
        const val gson = "com.squareup.retrofit2:converter-gson:${LibsVersion.Square.gson}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${LibsVersion.Square.retrofit}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${LibsVersion.Square.loggingInterceptor}"
    }
    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${LibsVersion.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${LibsVersion.coroutines}"
    }
    const val glide = "com.github.bumptech.glide:glide:${LibsVersion.glide}"
    const val circleimageview = "de.hdodenhof:circleimageview:${LibsVersion.circleimageview}"
    const val networkResponseAdapter = "com.github.haroldadmin:NetworkResponseAdapter:${LibsVersion.networkResponseAdapter}"
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