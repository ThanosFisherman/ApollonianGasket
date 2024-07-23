package io.github.thanosfisherman.gasket.android

import io.github.thanosfisherman.gasket.RealTimeSynth

class Tone : RealTimeSynth {

    override val now: Float
        get() = 0f
    override val state: String
        get() = "undefined"
    override val isLibraryLoaded: Boolean
        get() = false

    override fun loadLibrary() {
    }

    override fun createMonoSynth() {

    }

    override fun createPolySynth() {

    }

    override fun start() {
    }


    override fun play(note: String, duration: String, time: Float, velocity: Float) {

    }
}