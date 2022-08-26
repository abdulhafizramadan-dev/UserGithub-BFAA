import com.ahr.usergithub.ConfigurationData
import com.ahr.usergithub.ConfigurationData.applicationId
import com.ahr.usergithub.Libs
import com.ahr.usergithub.TestLibs
import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}

android {
    compileSdk = ConfigurationData.compileSdk

    val properties = Properties()
    properties.load(project.rootProject.file("local.properties").inputStream())

    defaultConfig {
        applicationId = ConfigurationData.applicationId
        minSdk = ConfigurationData.minSdk
        targetSdk = ConfigurationData.targetSdk
        versionCode = ConfigurationData.versionCode
        versionName = ConfigurationData.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", properties.getProperty("github.api.baseurl"))
        buildConfigField("String", "GITHUB_TOKEN", properties.getProperty("github.api.token"))
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // Jetpack Library
    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.appcompat)
    implementation(Libs.AndroidX.splashscreen)
    implementation(Libs.AndroidX.constraintlayout)
    implementation(Libs.AndroidX.navigationUi)
    implementation(Libs.AndroidX.navigationFragment)

    // KTX Library
    implementation(Libs.Ktx.livedataKtx)

    // Google Library
    implementation(Libs.Google.material)

    // Square Library
    implementation(Libs.Square.gson)
    implementation(Libs.Square.retrofit)
    implementation(Libs.Square.loggingInterceptor)

    // Coroutines Library
    implementation(Libs.Coroutines.core)
    implementation(Libs.Coroutines.android)

    // Networking Library
    implementation(Libs.networkResponseAdapter)
    implementation(Libs.glide)

    // View Library
    implementation(Libs.circleimageview)
    implementation(Libs.lottie)

    // Testing Library
    testImplementation(TestLibs.Junit.junit)
    androidTestImplementation(TestLibs.Junit.extJunit)
    androidTestImplementation(TestLibs.Espresso.espresso_core)
}