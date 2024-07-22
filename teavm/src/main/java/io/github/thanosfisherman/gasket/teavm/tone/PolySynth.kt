package io.github.thanosfisherman.gasket.teavm.tone

import org.teavm.jso.JSClass
import org.teavm.jso.JSMethod
import org.teavm.jso.JSObject

@JSClass
class PolySynth : JSObject {
    @JSMethod("toDestination")
    external fun toDestination(): PolySynth

    @JSMethod("triggerAttackRelease")
    external fun playSynth(note: String, duration: String)
}
