import kotlin.reflect.full.memberProperties

object Dependencies {

    object Plugins {
        // NOTE TO DEVS: Please keep alphabetical order for readability and extendability
        const val ANDROID_GRADLE_PLUGIN =
            "com.android.tools.build:gradle:${Versions.Plugins.agpVersion}"
        const val DESUGARING_PLUGIN =
            "com.android.tools:desugar_jdk_libs:${Versions.Plugins.desugaring}"
        const val GOOGLE_PLAY_SERVICES_GRADLE_PLUGIN =
            "com.google.gms:google-services:${Versions.Plugins.googlePlayServices}"

        const val KOTLIN_ALL_OPEN = "org.jetbrains.kotlin:kotlin-allopen:${Versions.kotlin}"
        const val KOTLIN_GRADLE_PLUGIN =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val KOTLIN_SERIALIZATION =
            "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"

        const val SAFE_ARGS_GRADLE_PLUGIN =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

        const val UNDERCOUCH = "de.undercouch:gradle-download-task:${Versions.Plugins.undercouch}"

        const val GRETTY_PLUGIN = "org.gretty:gretty:${Versions.Plugins.grettyVersion}"

        const val ANDROID_APPLICATION_APPLY = "com.android.application"
        const val ANDROID_LIBRARY_APPLY = "com.android.library"
        const val GOOGLE_PLAY_SERVICES_APPLY = "com.google.gms.google-services"
        const val JACOCO_APPLY = "jacoco"
        const val JAVA_LIBRARY_APPLY = "java-library"
        const val KOTLIN_ALL_OPEN_APPLY = "kotlin-allopen"
        const val KOTLIN_ANDROID_APPLY = "org.jetbrains.kotlin.android"
        const val KOTLIN_ANDROID_EXTENSIONS_APPLY = "kotlin-android-extensions"
        const val KOTLIN_JVM_APPLY = "org.jetbrains.kotlin.jvm"
        const val KOTLIN_KAPT_APPLY = "kotlin-kapt"
        const val KOTLIN_PARCELIZE_APPLY = "kotlin-parcelize"
        const val KOTLIN_SERIALIZATION_APPLY = "kotlinx-serialization"
        const val REALM_APPLY = "io.realm.kotlin"
        const val SAFE_ARGS_APPLY = "androidx.navigation.safeargs.kotlin"
        const val SONAR_PLUGIN_APPLY = "sonar-plugin"
        const val SONARQUBE_APPLY = "org.sonarqube"
        const val UNDERCOUCH_APPLY = "de.undercouch.download"
        const val GRETTY_APPLY = "org.gretty"
        // NOTE TO DEVS: Please keep alphabetical order for readability and extendability
    }

    object DesktopDependency {

        const val lwjgl3Backend = "com.badlogicgames.gdx:gdx-backend-lwjgl3:${Versions.Libgdx.gdxVersion}"
        const val nativesDesktop = "com.badlogicgames.gdx:gdx-platform:${Versions.Libgdx.gdxVersion}:natives-desktop"
        const val controllers =
            "com.badlogicgames.gdx-controllers:gdx-controllers-desktop:${Versions.Libgdx.gdxControllersVersion}"
        //const val miniAudio = "games.rednblack.miniaudio:miniaudio:${Versions.Libgdx.miniaudioVersion}:natives-desktop"

        fun getAll() =
            DesktopDependency::class.memberProperties.filter { it.isConst }.map { it.getter.call().toString() }.toSet()
    }

    object TeaVMDependency {

        const val gdxTeaVM = "com.github.xpenatan.gdx-teavm:backend-teavm:${Versions.Libgdx.gdxTeaVMVersion}"
        const val gdxTeaVMAssetLoader = "com.github.xpenatan.gdx-teavm:asset-loader:${Versions.Libgdx.gdxTeaVMVersion}"
//        const val gdxTeaVMFreeType =
//            "com.github.xpenatan.gdx-teavm:gdx-freetype-teavm:${Versions.Libgdx.gdxTeaVMVersion}"
//        const val box2d = "com.badlogicgames.gdx:gdx-box2d-gwt:${Versions.Libgdx.gdxVersion}"
//        const val bulletTeaVm = "com.github.xpenatan.gdx-teavm:gdx-bullet-teavm:${Versions.Libgdx.gdxTeaVMVersion}"
        fun getAll() =
            TeaVMDependency::class.memberProperties.filter { it.isConst }.map { it.getter.call().toString() }.toSet()
    }

    object AndroidDependency {

        const val androidBackend = "com.badlogicgames.gdx:gdx-backend-android:${Versions.Libgdx.gdxVersion}"
        const val controllers =
            "com.badlogicgames.gdx-controllers:gdx-controllers-android:${Versions.Libgdx.gdxControllersVersion}"

        //const val weSockets = "com.github.MrStahlfelge.gdx-websockets:common:${Versions.Libgdx.websocketVersion}"

        fun getAll() =
            AndroidDependency::class.memberProperties.filter { it.isConst }.map { it.getter.call().toString() }.toSet()
    }

    object AndroidNativesDependency {
        const val arm64v8a = "com.badlogicgames.gdx:gdx-platform:${Versions.Libgdx.gdxVersion}:natives-arm64-v8a"
        const val armeabiv7a = "com.badlogicgames.gdx:gdx-platform:${Versions.Libgdx.gdxVersion}:natives-armeabi-v7a"
        const val nativesX86 = "com.badlogicgames.gdx:gdx-platform:${Versions.Libgdx.gdxVersion}:natives-x86"
        const val nativesX86_64 = "com.badlogicgames.gdx:gdx-platform:${Versions.Libgdx.gdxVersion}:natives-x86_64"

//        const val box2d_arm64v8a =
//            "com.badlogicgames.gdx:gdx-box2d-platform:${Versions.Libgdx.gdxVersion}:natives-arm64-v8a"
//        const val box2d_armeabiv7a =
//            "com.badlogicgames.gdx:gdx-box2d-platform:${Versions.Libgdx.gdxVersion}:natives-armeabi-v7a"
//        const val box2d_nativesX86 =
//            "com.badlogicgames.gdx:gdx-box2d-platform:${Versions.Libgdx.gdxVersion}:natives-x86"
//        const val box2d_nativesX86_64 =
//            "com.badlogicgames.gdx:gdx-box2d-platform:${Versions.Libgdx.gdxVersion}:natives-x86_64"
//        const val bullet_arm64v8a =
//            "com.badlogicgames.gdx:gdx-bullet-platform:${Versions.Libgdx.gdxVersion}:natives-arm64-v8a"
//        const val bullet_armeabiv7a =
//            "com.badlogicgames.gdx:gdx-bullet-platform:${Versions.Libgdx.gdxVersion}:natives-armeabi-v7a"
//        const val bullet_nativesX86 =
//            "com.badlogicgames.gdx:gdx-bullet-platform:${Versions.Libgdx.gdxVersion}:natives-x86"
//        const val bullet_nativesX86_64 =
//            "com.badlogicgames.gdx:gdx-bullet-platform:${Versions.Libgdx.gdxVersion}:natives-x86_64"
//        const val freetype_arm64v8a =
//            "com.badlogicgames.gdx:gdx-freetype-platform:${Versions.Libgdx.gdxVersion}:natives-arm64-v8a"
//        const val freetype_armeabiv7a =
//            "com.badlogicgames.gdx:gdx-freetype-platform:${Versions.Libgdx.gdxVersion}:natives-armeabi-v7a"
//        const val freetype_nativesX86 =
//            "com.badlogicgames.gdx:gdx-freetype-platform:${Versions.Libgdx.gdxVersion}:natives-x86"
//        const val freetype_nativesX86_64 =
//            "com.badlogicgames.gdx:gdx-freetype-platform:${Versions.Libgdx.gdxVersion}:natives-x86_64"
//
//        const val miniaudio_arm64v8a =
//            "games.rednblack.miniaudio:miniaudio:${Versions.Libgdx.miniaudioVersion}:natives-arm64-v8a"
//        const val miniaudio_armeabiv7a =
//            "games.rednblack.miniaudio:miniaudio:${Versions.Libgdx.miniaudioVersion}:natives-armeabi-v7a"
//        const val miniaudio_nativesX86 =
//            "games.rednblack.miniaudio:miniaudio:${Versions.Libgdx.miniaudioVersion}:natives-x86"
//        const val miniaudio_nativesX86_64 =
//            "games.rednblack.miniaudio:miniaudio:${Versions.Libgdx.miniaudioVersion}:natives-x86_64"

        fun getAll() =
            AndroidNativesDependency::class.memberProperties.filter { it.isConst }.map { it.getter.call().toString() }
                .toSet()

    }

    object CoreDependency {

        const val coreLibgdx = "com.badlogicgames.gdx:gdx:${Versions.Libgdx.gdxVersion}"
        const val controllersCore =
            "com.badlogicgames.gdx-controllers:gdx-controllers-core:${Versions.Libgdx.gdxControllersVersion}"
        const val ashley = "com.badlogicgames.ashley:ashley:${Versions.Libgdx.ashleyVersion}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
//        const val controllerMapping = "de.golfgl.gdxcontrollerutils:gdx-controllerutils-mapping:${Versions.Libgdx.gdxControllerUtilsVersion}"
//        const val controllerScene2D = "de.golfgl.gdxcontrollerutils:gdx-controllerutils-scene2d:${Versions.Libgdx.gdxControllerUtilsVersion}"
        //const val ode4j = "com.github.antzGames:gdx-ode4j:${Versions.Libgdx.ode4jVersion}"

//        const val box2dLights = "com.badlogicgames.box2dlights:box2dlights:${Versions.Libgdx.box2dlightsVersion}"
//        const val gdxAi = "com.badlogicgames.gdx:gdx-ai:${Versions.Libgdx.aiVersion}"
//        const val box2d = "com.badlogicgames.gdx:gdx-box2d:${Versions.Libgdx.gdxVersion}"
//        const val bullet = "com.badlogicgames.gdx:gdx-bullet:${Versions.Libgdx.gdxVersion}"
//        const val gdxFreetype = "com.badlogicgames.gdx:gdx-freetype:${Versions.Libgdx.gdxVersion}"
//        const val kryo = "com.esotericsoftware:kryo:${Versions.Libgdx.kryoVersion}"
//        const val webSockets = "com.github.MrStahlfelge.gdx-websockets:core:${Versions.Libgdx.websocketVersion}"
//        const val kryonet = "com.github.crykn:kryonet:${Versions.Libgdx.kryoVersion}"
//        const val gdxGltf = "com.github.mgsx-dev.gdx-gltf:gltf:${Versions.Libgdx.gdxGltfVersion}"
//        const val digital = "com.github.tommyettinger:digital:${Versions.Libgdx.digitalVersion}"
//        const val funderby = "com.github.tommyettinger:funderby:${Versions.Libgdx.funderbyVersion}"
//        const val jdkgdxds = "com.github.tommyettinger:jdkgdxds:${Versions.Libgdx.jdkgdxdsVersion}"
//        const val kryoJdkgdxds = "com.github.tommyettinger:kryo-jdkgdxds:${Versions.Libgdx.kryoJdkgdxdsVersion}"
//        const val visUi = "com.kotcrab.vis:vis-ui:${Versions.Libgdx.visUiVersion}"
//        const val miniAudio = "games.rednblack.miniaudio:miniaudio:${Versions.Libgdx.miniaudioVersion}"
//        const val tweenEngine = "org.mini2Dx:universal-tween-engine:${Versions.Libgdx.universalTweenVersion}"

        fun getAll() =
            CoreDependency::class.memberProperties.filter { it.isConst }.map { it.getter.call().toString() }.toSet()
    }

    object CoreKtxDependency {

        const val ashley = "io.github.libktx:ktx-ashley:${Versions.Libgdx.ktxVersion}"
        const val ktxApp = "io.github.libktx:ktx-app:${Versions.Libgdx.ktxVersion}"
        const val ktxAssets = "io.github.libktx:ktx-assets:${Versions.Libgdx.ktxVersion}"
        const val ktxGraphics = "io.github.libktx:ktx-graphics:${Versions.Libgdx.ktxVersion}"
        const val ktxLog = "io.github.libktx:ktx-log:${Versions.Libgdx.ktxVersion}"
        const val ktxMath = "io.github.libktx:ktx-math:${Versions.Libgdx.ktxVersion}"
        const val ktxActors = "io.github.libktx:ktx-actors:${Versions.Libgdx.ktxVersion}"
        const val ktxAi = "io.github.libktx:ktx-ai:${Versions.Libgdx.ktxVersion}"

//        const val ktxAsync = "io.github.libktx:ktx-async:${Versions.Libgdx.ktxVersion}"
//        const val ktxBox2d = "io.github.libktx:ktx-box2d:${Versions.Libgdx.ktxVersion}"
//        const val ktxCollections = "io.github.libktx:ktx-collections:${Versions.Libgdx.ktxVersion}"
//        const val ktxFreetype = "io.github.libktx:ktx-freetype:${Versions.Libgdx.ktxVersion}"
//        const val ktxFreetypeAsync = "io.github.libktx:ktx-freetype-async:${Versions.Libgdx.ktxVersion}"
//        const val ktxi18n = "io.github.libktx:ktx-i18n:${Versions.Libgdx.ktxVersion}"
//        const val ktxInject = "io.github.libktx:ktx-inject:${Versions.Libgdx.ktxVersion}"
//        const val ktxJson = "io.github.libktx:ktx-json:${Versions.Libgdx.ktxVersion}"
//        const val ktxPreferences = "io.github.libktx:ktx-preferences:${Versions.Libgdx.ktxVersion}"
//        const val ktxReflect = "io.github.libktx:ktx-reflect:${Versions.Libgdx.ktxVersion}"
//        const val ktxScene2d = "io.github.libktx:ktx-scene2d:${Versions.Libgdx.ktxVersion}"
//        const val ktxScript = "io.github.libktx:ktx-script:${Versions.Libgdx.ktxVersion}"
//        const val ktxStyle = "io.github.libktx:ktx-style:${Versions.Libgdx.ktxVersion}"
//        const val ktxTiled = "io.github.libktx:ktx-tiled:${Versions.Libgdx.ktxVersion}"
//        const val ktxVis = "io.github.libktx:ktx-vis:${Versions.Libgdx.ktxVersion}"
//        const val ktxVisStyle = "io.github.libktx:ktx-vis-style:${Versions.Libgdx.ktxVersion}"
        //const val ktxArtemis = "io.github.libktx:ktx-artemis:${Versions.Libgdx.ktxVersion}"
        //const val ktxAssetsAsync = "io.github.libktx:ktx-assets-async:${Versions.Libgdx.ktxVersion}"

        fun getAll() =
            CoreKtxDependency::class.memberProperties.filter { it.isConst }.map { it.getter.call().toString() }.toSet()
    }

    object GraalDesktop {
        const val lwjgl3Backend = "com.github.Berstanio.gdx-graalhelper:gdx-svmhelper-backend-lwjgl3:${Versions.Libgdx.graalVersion}"
//        const val controllersDesktop =
//            "com.github.Berstanio.gdx-graalhelper:gdx-svmhelper-extension-controllers-lwjgl3:${Versions.Libgdx.graalVersion}"

        fun getAll() =
            GraalDesktop::class.memberProperties.filter { it.isConst }.map { it.getter.call().toString() }.toSet()
    }

    object GraalCore {
        const val graalCore = "io.github.berstanio:gdx-svmhelper:${Versions.Libgdx.graalVersion}"

        fun getAll() =
            GraalCore::class.memberProperties.filter { it.isConst }.map { it.getter.call().toString() }.toSet()
    }
}
