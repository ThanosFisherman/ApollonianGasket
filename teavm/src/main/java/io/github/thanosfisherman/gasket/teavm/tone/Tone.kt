package io.github.thanosfisherman.gasket.teavm.tone

import org.teavm.jso.JSBody
import org.teavm.jso.core.JSPromise

object Tone {
    @JvmStatic
    @JSBody(script = "return new Tone.getContext().state;")
    external fun state(): String

    @JvmStatic
    @JSBody(params = ["bpmVal"], script = "Tone.getTransport().bpm.value = bpmVal;")
    external fun setBPM(bpmVal: Int)

    @JvmStatic
    @JSBody(script = "return new Tone.start();")
    external fun start(): JSPromise<Void>

    @JvmStatic
    @JSBody(script = "return new Tone.Synth();")
    external fun createSynth(): Synth

    @JvmStatic
    @JSBody(
        script = """
           return new Tone.PolySynth(Tone.MonoSynth, {
				volume: -8,
				oscillator: {
					type: "square8",
				},
				envelope: {
					attack: 0.05,
					decay: 0.3,
					sustain: 0.4,
					release: 0.8,
				},
				filterEnvelope: {
					attack: 0.001,
					decay: 0.7,
					sustain: 0.1,
					release: 0.8,
					baseFrequency: 300,
					octaves: 4,
				},
			});
        """
    )
    external fun createPolySynth(): PolySynth

    @JvmStatic
    @JSBody(params = ["synth"], script = "synth.triggerAttackRelease('C4', '8n');")
    external fun playSynth(synth: Any)
}