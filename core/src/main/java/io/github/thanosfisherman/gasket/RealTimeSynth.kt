package io.github.thanosfisherman.gasket

interface RealTimeSynth {
    fun message(message: String)
    fun loadLibrary()
    fun start()
    fun state(): String
    fun play()
}