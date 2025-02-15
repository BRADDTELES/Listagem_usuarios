plugins {
   alias(libs.plugins.android.application)
   id("com.google.dagger.hilt.android")
}

android {
   namespace = "com.danilloteles.prototico_lista"
   compileSdk = 34

   defaultConfig {
      applicationId = "com.danilloteles.prototico_lista"
      minSdk = 24
      targetSdk = 34
      versionCode = 1
      versionName = "1.0"

      testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
      sourceCompatibility = JavaVersion.VERSION_1_8
      targetCompatibility = JavaVersion.VERSION_1_8
   }
   buildFeatures {
      viewBinding = true
   }
}

dependencies {

   //Hilt
   implementation("com.google.dagger:hilt-android:2.51.1")
   annotationProcessor("com.google.dagger:hilt-compiler:2.51.1")

   val lifecycle_version = "2.8.7"
   // ViewModel
   implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
   // LiveData
   implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

   //Android KTX: SQLite, Room, Palette, Navigation, Fragment, LiveData, ViewModel, Lifecycle, Collection, Core
   implementation("androidx.core:core-ktx:1.15.0")
   implementation("androidx.collection:collection-ktx:1.4.5")
   implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
   implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.5")
   implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
   implementation("androidx.fragment:fragment-ktx:1.8.3")
   implementation("androidx.navigation:navigation-runtime-ktx:2.8.6")
   implementation("androidx.navigation:navigation-fragment-ktx:2.8.6")
   implementation("androidx.navigation:navigation-ui-ktx:2.8.6")
   implementation("androidx.palette:palette-ktx:1.0.0")
   implementation("androidx.room:room-ktx:2.6.1")
   implementation("androidx.sqlite:sqlite-ktx:2.4.0")

   implementation(libs.appcompat)
   implementation(libs.material)
   implementation(libs.activity)
   implementation(libs.constraintlayout)
   testImplementation(libs.junit)
   androidTestImplementation(libs.ext.junit)
   androidTestImplementation(libs.espresso.core)
}