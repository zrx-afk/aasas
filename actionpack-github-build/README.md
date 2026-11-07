# Action Pack (Fabric, Minecraft Java 26.3)
Needs JDK 25.

Build (easiest): copy src/, build.gradle, settings.gradle and gradle.properties over a fresh
clone of https://github.com/FabricMC/fabric-example-mod (branch for 26.3 / latest) so you get the Gradle wrapper, then:
    ./gradlew build        -> build/libs/actionpack-1.0.0.jar
    ./gradlew runClient    -> test in-game
Install: put the jar in .minecraft/mods with Fabric Loader 0.19.5+ and Fabric API for 26.3.

Items (Combat tab):
- Dash Blade: netherite-level sword. Right-click to dash forward (2s cooldown).
  Craft: diamond sword + 4 feathers (shapeless).
- Thunder Staff: right-click calls 3 lightning bolts ahead (5s cooldown).
  Craft: lightning rod at top-right, blaze rods on the diagonal.
