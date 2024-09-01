# Meta World Type

## About

MetaWorldType adds a world type in the world creation screen, that calls _other_ world types to generate all in their own regions of the world. This allows you to have several other world types and even other dimensions, all generated in their own areas in the overworld!! For example, you could walk on foot between default areas, amplified areas, twilight forest areas, et al.

The world gets divided into a grid of regions, regions having a configurable length x width. Every region is weighted-randomly picked from a configurable list of possible world types or dimensions to generate from.

This mod doesn't have any world generation of its own, it just defers the generation to other generators depending on world coordinates.

## Config (read)

When loading into a world using the MetaWorldType, a list of possible generator strings will be spewed into the log. For example:

```
[com.kpabr.GeneratorSplicer.SplicedWorldType:getChunkGenerator:68]: worldtype 4: owg.world.WorldTypeOWG@c444117 , 4 ; OWG
[20:21:58] [Server thread/INFO] [STDOUT]: [com.kpabr.GeneratorSplicer.SplicedWorldType:getChunkGenerator:68]: worldtype 5: ttftcuts.atg.ATGWorldType@17e992f7 , 5 ; ATG
[20:21:58] [Server thread/INFO] [STDOUT]: [com.kpabr.GeneratorSplicer.SplicedWorldType:getChunkGenerator:68]: worldtype 9: biomesoplenty.common.world.WorldTypeBOP@7c36f936 , 9 ; BIOMESOP
[20:21:59] [Server thread/INFO] [STDOUT]: [com.kpabr.GeneratorSplicer.ChunkProviderSpliced:<init>:255]: Dimension: 20, Name: thebetweenlands.world.WorldProviderBetweenlands
[20:21:59] [Server thread/INFO] [STDOUT]: [com.kpabr.GeneratorSplicer.ChunkProviderSpliced:<init>:255]: Dimension: 7, Name: twilightforest.world.WorldProviderTwilightForest
[20:21:59] [Server thread/INFO] [STDOUT]: [com.kpabr.GeneratorSplicer.ChunkProviderSpliced:<init>:255]: Dimension: -127, Name: net.tropicraft.world.WorldProviderTropicraft
```

Valid generator names are always the last string printed, for example: `OWG`, `ATG`, `BIOMESOP`, `thebetweenlands.world.WorldProviderBetweenlands`, `twilightforest.world.WorldProviderTwilightForest`, `net.tropicraft.world.WorldProviderTropicraft` would all be valid generator names.

There are also a few builtin generator options, all from vanilla. These are `DEFAULT`, `AMPLIFIED`, `LARGE_BIOMES`, `FLAT`, `DEFAULT_1_1`, `HELL`, and `END`.

All lines _must_ end with a probability to spawn, probability being `n/1`. The `@` character deliminates the probability chance, so `AMPLIFIED@0.25` will give each world region an ~25% chance to be amplified. Note that I use noise instead of random, so the same entry / entries next to each other in the list are more likely to spawn next to each other. You will probably want the sum of all the probabilities to be equal to `1`. 

All generator types can optionally be followed with a custom generator string, delimited by a `+`. For example, the mod OWG has generator strings the user can set like `INFDEV#1` to select an infdev terrain generator. So if you want a ~10% chance for a region to be snowy infdev, and a 20% chance for regular infdev, you would have the entries `OWG+INFDEV#1@0.1` and `OWG+INFDEV#0@0.2`. The generator string has to be before the spawn chance.

The first entry in the list will also be used for world spawn. It has to be a world type not from a dimension mod, so `DEFAULT`, `BIOMESOP`, `HELL`, and `ATG` would be valid first options, but not say, twilight forest. If you want the first entry to spawn only in world spawn, and not elsewhere, you can give it a spawn chance of `0`. 

## Q & A

##### Help! My RAM usage is higher!!

From how the mod is written, it will be using higher ram serverside than base forge. A newer version of java, say 17 or 21, basically eliminates the ram useage of this mod (and makes it more performant in general). Yes, you can install modern java on forge 1.7.10 with [lwjgl3ify](https://github.com/GTNewHorizons/lwjgl3ify), which will make your ram useage a fraction of what it is with java 8.

##### RTG is broke

Yeah, upstream RTG will have no populator when spawned by this mod (meaning, no trees, no ores, etc). I have a forked version on my [GitHub](https://github.com/Richard-Rogalski) that 'fixes' this. RTG changes its decorator depending on the neighboring chunks. It has a check to only populate when all its neighbors are, which as you can tell, doesn't really happen with this mod. My patch just makes it, load all other chunks on demand, which will make the server freeze for up to 30 seconds when entering an RTG region. Yes, this isn't ideal, but at least it's only a (mostly) one time lagspike per region, as it goes and generates a whole boatload of chunks at once. Not happy with this solution but it's the solution I got.

##### Betweenlands is broke

I honestly forgot what I did to fix this one, but have a forked version on my [GitHub](https://github.com/Richard-Rogalski). The custom betweenlands shaders and music won't play when in betweenlands chunks in the overworld. The populator doesn't seem to populate as much, and mobs really don't spawn. I'll try to fix this eventually. 

##### Tropicraft is broke

It shouldn't be now that I fixed a bug, but I have an updated version on my [GitHub](https://github.com/Richard-Rogalski) that fixes a few unrelated bugs, and has features newer than the last official 1.7.10 tropicraft release. But yeah normal tropicraft should be working.

##### Mystcraft is broke

I know, it would be really cool if it worked with my mod. I might work on it one day.

##### Twilight Forest is broke

Use the [GTNH fork](https://github.com/GTNewHorizons/twilightforest). Note that you cannot use the magic map in the overworld (i tried to allow it but it just kinda corrupts nearby chunks), and you can only gain achievements while in the TF dimension.

##### Biomes O Plenty is broke

BOP only spawns like 5% of its biomes for some reason when they're spawned by this mod? I'm planning a fix.

##### Why a million forks and not just directly mixin to the mods?

Because I'm lazy. I'll hopefully get around to mixin-ifying all my fixes before the first stable release. No promises.

##### The borders between regions are fairly ugly and rough, do you have plans to smooth out border regions like 1.19 does?

That sounds like a lot of work, something even mojang couldn't do smoothly, and something I wouldn't use as I like the rough borders.

##### Why 1.7.10 it's ten years old and outdated and abandoned nobody uses it and please port to 1.30 eta wen and there's no soydium and doesn't have create and and and and an

Because I play it. The code is open source, if you want to port it to modern minecraft, you're more than welcome to. Word of advice though-- if you care about your sanity, don't. World generation has changed a lot over versions, and this mod relies on weird erratic behavior of every corner. If you do want to port it, memorizing all of minecraft's world generation code is a probable prerequisite. 

##### Do you have a discord or IRC?

I have a thread in the #mods room on the [Legacy Modding discord](https://discord.gg/vFhf7acgMK). No IRC room but I'm 'rrogalski' on libera and OFTC. Or just use github issues idc.
