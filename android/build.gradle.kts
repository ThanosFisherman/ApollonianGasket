plugins {
    id("com.android.application")
    //id("org.jetbrains.kotlin.android")
    kotlin("android")
}

android {
    namespace = "io.github.thanosfisherman.game.android"
    compileSdk = 34

    sourceSets {
        getByName("main") {
            manifest.srcFile("src/main/AndroidManifest.xml")
            java.srcDirs("src/main/java")
            kotlin.srcDirs("src/main/kotlin")
            aidl.srcDirs("src/main/java")
            renderscript.srcDirs("src/main/java")
            res.srcDirs("res")
            assets.srcDirs("../assets")
            jniLibs.srcDirs("libs")
        }

        getByName("test") {
            setRoot("src/test")
            assets.srcDirs("src/test/assets")
        }

        testOptions {
            unitTests.isReturnDefaultValues = true
            /**
             * Gradle Daemons that is executing the tests on the build server has Java heap size set
             * to 512MB by default, changing it to 4GB is equal to setup with local machine with 16GB
             * and prevents the test to run into OutOfMemory Errors in tests
             */
            unitTests.all {
                it.jvmArgs("-Xmx8g")
            }
        }
    }

    defaultConfig {
        applicationId = "io.github.thanosfisherman.game.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
//    buildFeatures {
//        compose = true
//    }
//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.4.3"
//    }
    packaging {
        resources {
            excludes.apply {
                add("/META-INF/{AL2.0,LGPL2.1}")
                add("META-INF/robovm/ios/robovm.xml")
                add("META-INF/DEPENDENCIES.txt")
                add("META-INF/DEPENDENCIES")
                add("META-INF/dependencies.txt")
                add("**/*.gwt.xml")
            }
            pickFirsts.apply {
                add("META-INF/LICENSE.txt")
                add("META-INF/LICENSE")
                add("META-INF/license.txt")
                add("META-INF/LGPL2.1")
                add("META-INF/NOTICE.txt")
                add("META-INF/NOTICE")
                add("META-INF/notice.txt")
            }
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    implementation("androidx.activity:activity-compose:1.8.0")
    //implementation(platform("androidx.compose:compose-bom:2023.03.00"))
//    implementation("androidx.compose.ui:ui")
//    implementation("androidx.compose.ui:ui-graphics")
//    implementation("androidx.compose.ui:ui-tooling-preview")
//    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    // androidTestImplementation("androidx.compose.ui:ui-test-junit4")
//    debugImplementation("androidx.compose.ui:ui-tooling")
//    debugImplementation("androidx.compose.ui:ui-test-manifest")
}