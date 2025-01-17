plugins {
    `base-plugin-kotlin`
}

tasks.test {
    useJUnitPlatform()
}

dependencies {
    addCoreDependencies()
    addCoreKtxDependencies()
    addGraalCoreDependencies()
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}
