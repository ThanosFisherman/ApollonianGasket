package io.github.thanosfisherman.gasket.desktop

import io.github.thanosfisherman.gasket.RealTimeSynth

class Tone: RealTimeSynth {
    override fun message(message: String) {
        println(message)
    }

    override fun loadLibrary() {
    }

    override fun start() {
    }

    override fun state(): String {
        return "undefined"
    }

    override fun play(note: String, duration: String) {

    }
}