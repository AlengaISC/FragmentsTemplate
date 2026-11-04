// app/build.gradle.kts: aquí los plugins se APLICAN.
// - Sin org.jetbrains.kotlin.android: desde AGP 9.0 el soporte de Kotlin va integrado
//   y aplicarlo además da error.
// - Safe Args: sin versión (ya la puso la raíz), sin apply false y SIEMPRE el último.
plugins {
    alias(libs.plugins.android.application)
    id("androidx.navigation.safeargs.kotlin")
}

android {
    // CAMBIAR: paquete de la app (namespace y applicationId, iguales)
    namespace = "es.sanclemente.navigationtemplate"
    compileSdk = 36

    defaultConfig {
        applicationId = "es.sanclemente.navigationtemplate"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
}
