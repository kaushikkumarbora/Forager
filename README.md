# BaritoneModule
Module for the [Macro/Keybinding](https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1275039-macro-keybind-mod) modulation for [minecraft/liteloader](https://www.liteloader.com/) using [Baritone API](https://github.com/cabaletta/baritone) with some other scripts. This project acn also be used as an example to make your own module. The scripts use some functions from other modules created by [spthiel](https://github.com/spthiel). 

###Features:
* Stand-alone ForgeGradle project which automatically fetches:
    * [LiteLoader](https://www.liteloader.com/)
    * Macros Mod (dev-version)
    * Macros API
* Provides a test environment for your module and builds a compatible artefact with no customisation required.
* Only supports ModMacros 0.15.4 for MC 1.12.1 currently, as new versions are released in the future, I will try to update this.
* Easy to port to a new version.

###Other Modules:
* [NotEnoughInformation](https://github.com/spthiel/NotEnoughInformation/)
* [Baritone](https://github.com/cabaletta/baritone)

###Usage:
1. Install LiteLoader for Minecraft 1.12.1 from [here](https://www.liteloader.com/download).
2. Download Macro/Keybind Mod 0.15.4 from [here](https://www.minecraftforum.net/forums/mapping-and-modding-java-edition/minecraft-mods/1275039-macro-keybind-mod#dl) and paste the `.litemod` file in `%appdata%\.minecraft\mods\` folder.
3. Download NEI module from [here](https://github.com/spthiel/Modules/blob/master/modules/nei/module_NotEnoughInformation-1.1.1-26.1-0.15.4-mc1.12.1.jar), the baritone module and put them in `%appdata%\.minecraft\liteconfig\common\macros\modules` folder.
4. Download Baritone from [here](https://github.com/cabaletta/baritone/releases/download/v1.2.14/baritone-api-forge-1.2.14.jar) and put it in `%appdata%\.minecraft\mods\1.12.1` folder. If you don't see a `1.12.1` folder, just create one.
5. Download the scripts in this repo and paste them in `%appdata%\.minecraft\liteconfig\common\macros` folder.
6. Launch Minecraft Installation version `release 1.12.1-LiteLoader1.12.1` from MC Launcher.
7. Configure the scripts and use them. You might need to change some harcoded co-ordinates and directions and you are good to go.

###Version Change:

* If you want to use newer versions of macrosapi and macrosdev, edit the `macros` line in your `gradle.properties` to reference the new version (eg. "0.16.0_for_1.13.1").
* Run `setupDecompWorkspace` task and refresh Gradle Project by clicking on `Reimport All Gradle Projects`.
   
###Video:

[![Forager in Action](http://img.youtube.com/vi/bgWFh9VhhUY/0.jpg)](https://youtu.be/bgWFh9VhhUY)

[![Harpbot in Action](http://img.youtube.com/vi/b2HJrHl6v4k/0.jpg)](https://youtu.be/b2HJrHl6v4k)