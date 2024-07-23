package io.github.thanosfisherman.gasket.teavm.tone

import org.teavm.jso.JSClass
import org.teavm.jso.JSMethod
import org.teavm.jso.JSObject
import org.teavm.jso.JSProperty

@JSClass
abstract class Synth() : JSObject {

    @JSMethod("triggerAttackRelease")
    external fun playSynth(note: String, duration: String, time: Float, velocity: Float)

    @JSProperty("maxPolyphony")
    external fun maxPolyphony(max: Int = 32)
}