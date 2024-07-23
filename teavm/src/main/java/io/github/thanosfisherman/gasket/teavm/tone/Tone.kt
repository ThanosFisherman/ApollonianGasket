package io.github.thanosfisherman.gasket.teavm.tone

import org.teavm.jso.JSBody
import org.teavm.jso.core.JSPromise

object Tone {

    @JvmStatic
    @JSBody(script = "return new Tone.getContext().state;")
    external fun state(): String

    @JvmStatic
    @JSBody(script = "return Tone.now();")
    external fun now(): Float

    @JvmStatic
    @JSBody(script = "return new Tone.start();")
    external fun start(): JSPromise<Void>

    @JvmStatic
    @JSBody(script = "return new Tone.Synth().toDestination();")
    external fun createSynth(): Synth

    @JvmStatic
    @JSBody(
        script = """
           return new Tone.PolySynth(Tone.FMSynth, {
                         harmonicity: 8,
                         modulationIndex: 2,
                         oscillator: {
                           type: "sine"
                         },
                         envelope: {
                           attack: 0.001,
                           decay: 2,
                           sustain: 0.2,
                           release: 4
                         },
                         modulation: {
                           type: "square"
                         },
                         modulationEnvelope: {
                           attack: 0.002,
                           decay: 0.2,
                           sustain: 0,
                           release: 0.2
                         }
                      }).toDestination();
        """
    )
    external fun createPolySynth(): Synth

    @JvmStatic
    @JSBody(params = ["synth"], script = "synth.triggerAttackRelease('C4', '8n');")
    external fun playC4(synth: Any)
}