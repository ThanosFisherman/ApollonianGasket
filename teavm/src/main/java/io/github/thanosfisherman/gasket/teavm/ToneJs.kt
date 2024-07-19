package io.github.thanosfisherman.gasket.teavm

import io.github.thanosfisherman.gasket.Synth
import org.teavm.jso.JSBody

@JSBody(params = ["message"], script = "console.log(message);")
external fun log(message: String?)

class ToneJs : Synth {


    override fun message(message: String) {
        log(message)
    }


    override fun play() {

    }
}