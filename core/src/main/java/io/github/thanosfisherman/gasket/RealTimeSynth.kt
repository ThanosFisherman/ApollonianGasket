package io.github.thanosfisherman.gasket

interface RealTimeSynth {
    val now: Float
    val state: String
    val isLibraryLoaded: Boolean

    fun loadLibrary()
    fun createMonoSynth()
    fun createPolySynth()
    fun start()
    fun play(note: String, duration: String, time: Float, velocity: Float = 1.0f)
}