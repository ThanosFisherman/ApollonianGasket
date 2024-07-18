#!/bin/bash
/home/thanos/.jdks/graalvm-jdk-21.0.3/bin/native-image -Dfile.encoding=UTF8 -H:+UnlockExperimentalVMOptions -H:IncludeResources="((data)/.*)|(.*\.glsl)|(.*\.so)|(com/badlogic/gdx/utils/.*\.(png|fnt))" -jar /home/thanos/IntellijProjects/automata/ApollonianGasket/desktop/build/lib/desktop-1.1.1.jar
#/home/thanos/.jdks/graalvm-ce-java17-22.3.3/bin/native-image -Dfile.encoding=UTF8 -H:IncludeResources="((data)/.*)|(.*\.so)|(com/badlogic/gdx/utils/.*\.(png|fnt))" -jar /home/thanos/IntellijProjects/GameDev/LibGDX/demo-invaders-test/lwjgl3/build/lib/libgdx-demo-invaders-1.0.0.jar
