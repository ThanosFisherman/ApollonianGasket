package io.github.thanosfisherman.game.teavm

import com.github.xpenatan.gdx.backends.teavm.assetloader.AssetLoader
import com.github.xpenatan.gdx.backends.teavm.assetloader.AssetLoaderListener


class JSLibraryLoader {


    fun load(libraryName: String) {
        loadInternal(libraryName, null)
    }

    fun load(libraryName: String, listener: ((isLoaded: Boolean) -> Unit)?) {
        loadInternal(libraryName, listener)
    }

    private fun loadInternal(libraryName: String, listener: ((isLoaded: Boolean) -> Unit)?) {
        var libName = libraryName
        if (!libraryName.endsWith(".js")) {
            libName = "$libraryName.js"
        }

        if (loadedLibraries.contains(libName)) {
            return
        }
        loadedLibraries.add(libName)
        val instance: AssetLoader.AssetLoad = AssetLoader.getInstance()

        if (listener != null) {
            instance.loadScript(true, libName, object : AssetLoaderListener<String> {

                override fun onFailure(url: String) {
                    listener(false)
                }

                override fun onSuccess(url: String, result: String) {
                    listener(true)
                }
            })
        } else {
            instance.loadScript(false, libName, null)
        }
    }

    companion object {
        private val loadedLibraries = HashSet<String>()
    }
}
