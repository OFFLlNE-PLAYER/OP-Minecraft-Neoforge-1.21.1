==================== OP-Minecraft-NeoForge-1.21.1 ====================
||| AUDIENCE |||
Advanced mod developers familiar with Minecraft 1.21.1, NeoForge, Gradle, and Java 21.
=======================================================================

||| 1) BUILD / CONFIGURATION |||
- Java Toolchain: Java 21 required (build.gradle -> java.toolchain.languageVersion = 21).
- Gradle Wrapper: Use provided wrapper only.
  - Windows: .\gradlew.bat tasks
  - Any OS: ./gradlew tasks
- Core toolchain/deps:
  - NeoForge: version from gradle.properties (neo_version=21.1.x). Parchment mappings configured.
  - Mixin: org.spongepowered:mixin 0.8.5 (+ processor) with refmap path src/main/resources/opminecraft.refmap.json.
  - Additional libs pinned: Guava, Gson, ASM.
- Coordinates: group/id/version/name/license/etc. from gradle.properties.
- Generated metadata:
  - Task generateModMetadata expands src/main/templates -> build/generated/sources/modMetadata.
  - Resources include the generated dir automatically (neoForge.ideSyncTask generateModMetadata runs on IDE reload).
- Run configs (NeoForge):
  - runClient | runServer (with --nogui) | runData | runGameTestServer.
  - Logging: forge.logging.markers=REGISTRIES, console logLevel DEBUG.
- Performance: org.gradle.jvmargs=-Xmx4G (adjust locally if needed).
-----------------------------------------------------------------------

||| 2) SOURCE / ASSETS LAYOUT |||
- Java: src\main\java
- Resources: src\main\resources (assets + data).
  - Generated: build\generated\sources\modMetadata is added to resources at build/sync time.
- Namespace: opminecraft (from mod_id). Use lowercase with underscores for registry ids.
- Central Registration: net.offllneplayer.opminecraft.init.RegistryBIBI
  - Pattern: public static final DeferredBlock<Block> NAME = registerBlock("name", Supplier<Block>).
  - Keep ids aligned across: blockstates, models, recipes, loot tables, tags.
- Mixins: If adding new mixins, keep refmap path consistent and ensure your mixin json references the same file. Re-run builds so mixin-out.srg regenerates (custom.srg used for reobf).
-----------------------------------------------------------------------

||| 3) BUILDING AND RUNNING |||
- * do not build unless unconfirmed changes are present.
- * consider build successful if there are no errors in all edited files.
- * if build succeeds or is considered successful as described above, do not build again.
- 
- Full build: .\gradlew.bat build
- Local publish: .\gradlew.bat publishToMavenLocal (or publishes to repo/ via maven-publish).
- 
- * Dev runs: (do not run, EVER)
  - Client: .\gradlew.bat runClient
  - Server: .\gradlew.bat runServer --args "--nogui"
  - Data gen: .\gradlew.bat runData
  - GameTest server: .\gradlew.bat runGameTestServer (runs tests then exits; see quirks below).
-----------------------------------------------------------------------

||| 4) TESTING |||
There are two complementary approaches.

A) Minecraft GameTest (in-world)
- gameTestServer is preconfigured. It enables tests for namespace = ${mod_id} via neoforge.enabledGameTestNamespaces.
- Recommended source: src\test\java. Bind tests to the mod in build.gradle:

  neoForge {
    mods {
      "${mod_id}" {
        sourceSet(sourceSets.main)
        // include tests in the game test run
        sourceSet(sourceSets.test)
      }
    }
  }

- GameTest (1.21.1):

*** Do not prompt to run client tests in junie tasks. Do not try run them. User will run testing. ***

-----------------------------------------------------------------------

||| 5) PROJECT-SPECIFIC NOTES & QUIRKS |||
[Quirk] Loot tables path (NeoForge 1.21.1): data/<namespace>/loot_table/... (singular, no trailing 's').
Example in this repo: data/opminecraft/loot_table/blocks/slate.json.
[Quirk] Recipes path (NeoForge 1.21.1):  data/<namespace>/recipe/... (matches current repo layout).
[Quirk] Central registry patterns in RegistryBIBI:
  - Uses helper registerBlock("id", Supplier<Block>) for many families (slate, crying tiles, cobbled/smooth variants, etc.). Add full variant sets to keep parity across blockstates/models/recipes/loot tables.
[Quirk] Tagging: custom item tag exists at data/opminecraft/tags/item/mixin_non_rotate_items.json. When adding items that should not rotate (ALL 3d item models), extend this tag accordingly.
[Quirk] GameTest behavior: the provided run config will crash/exit with failure if no tests are registered. Add at least one trivial test (see BasicGameTests) when using runGameTestServer.
[Quirk] Mixins setup: compiler args write mixin-out.srg and use custom.srg for reobf; ensure your mixin config(s) point to src/main/resources/opminecraft.refmap.json to stay aligned with build.gradle.
[Quirk] Generated mod metadata: do not edit generated files under build/generated/sources/modMetadata; change values in gradle.properties or src/main/templates and re-run sync/build.
[Quirk] Publishing: maven-publish is wired to a local file repo under repo/ for easy local integration testing.
[Quirk] Logging: only REGISTRIES marker is enabled by default. If you need deep dumps, temporarily add REGISTRYDUMP to forge.logging.markers inside the run config block.
-----------------------------------------------------------------------

||| 6) QUICK VERIFICATION PATHS |||
- GameTests (recommended for MC integration):
  1) Add sourceSet(sourceSets.test) under neoForge.mods for ${mod_id}.
  2) Add BasicGameTests in src\test\java.
  3) Run .\gradlew.bat runGameTestServer.
  4) Verify success in console and run\logs\latest.log.
- JUnit (pure Java logic):
  1) Temporarily add JUnit deps and useJUnitPlatform() in build.gradle.
  2) Create BasicUnitTest in src\test\java.
  3) Run .\gradlew.bat test.
  4) Revert changes if keeping repo free of JUnit.
-----------------------------------------------------------------------

||| 7) CLEANUP POLICY |||
- If you're not using a feature/field/method, remove it. Do not mark anything deprecated. Ever.
- Avoid committing anything temporary to final build. Keep the repo clean.
- This document is the only persistent guidance. Keep notes in the .Junie package and remove all notes after the current task.
=======================================================================
