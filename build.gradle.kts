// build.gradle.kts (raíz del proyecto): aquí los plugins solo se DECLARAN (apply false).
// La versión de Safe Args sale del catálogo: es la MISMA línea "navigation" que usan
// las bibliotecas, así que no pueden desincronizarse.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.navigation.safeargs) apply false
}
