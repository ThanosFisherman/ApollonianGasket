package io.github.thanosfisherman.gasket.teavm.tone

import org.teavm.jso.JSClass
import org.teavm.jso.JSMethod
import org.teavm.jso.JSObject

@JSClass
class Synth : JSObject {
    @JSMethod("toDestination")
    external fun toDestination(): Synth


    //    note: Frequency,
    //    duration: Time,
    //    time?: Time,
    //    velocity?: NormalRange
    @JSMethod("triggerAttackRelease")
    external fun playSynth(note: String, duration: String)
}
