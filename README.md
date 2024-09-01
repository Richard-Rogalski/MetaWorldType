# Meta World Type

## About

ughhhh I'll write this later

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

All lines _must_ end with a probability to spawn, probability being `n/1`. The `@` character deliminates the probability chance, so `AMPLIFIED@0.25` will give each world region an ~25% chance to be amplified. Note that I use noise instead of random, so the same entry / entries next to each other in the list are more likely to spawn next to each other. You will probably want the sum of all the probabilities to be equal to 1. 

All generator types can optionally be followed with a custom generator string, delimited by a `+`. For example, the mod OWG has generator strings the user can set like `INFDEV#1` to select an infdev terrain generator. So if you want a ~10% chance for a region to be snowy infdev, and a 20% chance for regular infdev, you would have the entries `OWG+INFDEV#1@0.1` and `OWG+INFDEV#0@0.2`. The generator string has to be before the spawn chance.

The first entry in the list will also be used for world spawn. It has to be a world type not from a dimension mod, so `DEFAULT`, `BIOMESOP`, and `ATG` would be valid first options, but not say twilight forest. If you want the first entry to spawn only in world spawn, and not elsewhere, you can give it a spawn chance of `0`. 
