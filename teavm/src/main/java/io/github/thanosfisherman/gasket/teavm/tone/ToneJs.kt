package io.github.thanosfisherman.gasket.teavm.tone

import io.github.thanosfisherman.gasket.RealTimeSynth
import io.github.thanosfisherman.gasket.teavm.JSLibraryLoader
import org.teavm.jso.JSBody

@JSBody(params = ["message"], script = "console.log(message);")
external fun log(message: String)

class ToneJs : RealTimeSynth {

    private val jsLibraryLoader = JSLibraryLoader()
    private var isLoaded: Boolean = false
    private var monoSynth: Synth? = null
    private var polySynth: Synth? = null

    override val now: Float
        get() = Tone.now()
    override val state: String
        get() = Tone.state()
    override val isLibraryLoaded: Boolean
        get() = isLoaded

    override fun loadLibrary() {
        jsLibraryLoader.load("Tone.js")
        isLoaded = true
    }

    override fun createMonoSynth() {
        if (monoSynth == null) {
            monoSynth = Tone.createSynth()
        }
    }

    override fun createPolySynth() {
        if (polySynth == null) {
            polySynth = Tone.createPolySynth()
            polySynth?.maxPolyphony(60)
            Tone.setBpm(300)
        }
    }

    override fun start() {
        if (state != "running") {
            Tone.start()
        }
    }

    override fun play(note: String, duration: String, time: Float, velocity: Float) {
        monoSynth?.playSynth(note, duration, time, velocity)
        polySynth?.playSynth(note, duration, time, velocity)
    }
}