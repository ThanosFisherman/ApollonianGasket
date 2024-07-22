package io.github.thanosfisherman.gasket.teavm.tone

import io.github.thanosfisherman.gasket.RealTimeSynth
import io.github.thanosfisherman.gasket.teavm.JSLibraryLoader
import org.teavm.jso.JSBody

@JSBody(params = ["message"], script = "console.log(message);")
external fun log(message: String)

class ToneJs : RealTimeSynth {

    private val jsLibraryLoader = JSLibraryLoader()
    private var isLoaded = false

    override fun message(message: String) {
        log(message)
    }

    override fun loadLibrary() {
        jsLibraryLoader.load("Tone.js") {
            log("TONE JS initialized: $it")
            isLoaded = it
        }
    }

    override fun start() {
        if (state() != "running")
            Tone.start()
    }

    override fun state(): String {
        return Tone.state()
    }

    override fun play() {
        val synth = Tone.createSynth().toDestination()
        Tone.playSynth(synth)
    }
}