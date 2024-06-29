package com.kpabr.GeneratorSplicer;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.ChunkProviderEnd;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.*;
import static net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.*;
import net.minecraftforge.common.*;
import cpw.mods.fml.common.eventhandler.Event.*;
import net.minecraftforge.event.terraingen.*;
import net.minecraft.world.WorldType;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.World;
import java.lang.reflect.Method;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderServer;
import java.util.Map;
import java.util.HashMap;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.SaveFormatOld;
import net.minecraft.world.storage.WorldInfo;
import net.minecraft.world.storage.SaveHandlerMP;
import net.minecraft.world.storage.SaveHandler;
import java.io.File;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import java.lang.reflect.Field;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import net.minecraft.world.WorldProvider;


import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.profiler.Profiler;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.Vec3;
import net.minecraft.village.VillageCollection;
import net.minecraft.village.VillageSiege;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldInfo;

import cpw.mods.fml.common.FMLLog;

import com.google.common.collect.ImmutableSetMultimap;

import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.common.WorldSpecificSaveHandler;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraft.entity.EnumCreatureType;


public class ChunkProviderSpliced implements IChunkProvider{

    private final World world;
	private final long seed;
    private final Random random;
	private final boolean mapFeaturesEnabled;
    private final ChunkProviderGenerate amplifiedProvider;
    private final ChunkProviderGenerate largeBiomesProvider;
    private final ChunkProviderGenerate normal;
    private final ChunkProviderGenerate DEFAULT_1_1;
    private final ChunkProviderHell hell;
    private final ChunkProviderEnd end;
	private final ChunkProviderFlat flat;
	private final WorldChunkManager worldChunkMgr;
	private final WorldType worldTypeTmp;
	private final String generatorStrTmp;
	public static Field generatorOptionsField;
	//public static final MethodHandle generatorOptionsMeth;
	private Map<String, WorldServer> worldDict;

	//private final Worldtype1 = WorldType.parseWorldType("DEFAULT");
	//private final Worldtype2 = WorldType.parseWorldType("AMPLIFIED");

    public ChunkProviderSpliced(World world, long seed, boolean mapFeaturesEnabled) {
        this.world = world;
		this.seed = seed;
        this.random = new Random(seed);
		this.mapFeaturesEnabled = mapFeaturesEnabled;

		worldDict = new HashMap<>();

		worldTypeTmp = world.getWorldInfo().getTerrainType(); // TODO actually set this
		generatorStrTmp = world.getWorldInfo().getGeneratorOptions();
		//generatorStrTmp = world.getWorldInfo().getGeneratorOptions();
		WorldInfo worldInfo = world.getWorldInfo();
		//System.out.println("WORLD TYPE: " + worldTypeTmp);

		this.worldChunkMgr = world.getWorldChunkManager(); // TODO actually set this
		//System.out.println("ChUNK MANAGER: " + worldChunkManagerTmp);

        // Initialize other chunk providers (Amplified and Large Biomes)
        this.normal = new ChunkProviderGenerate(world, seed, world.getWorldInfo().isMapFeaturesEnabled());

		worldInfo.setTerrainType(WorldType.AMPLIFIED);
        this.amplifiedProvider = new ChunkProviderGenerate(world, seed, world.getWorldInfo().isMapFeaturesEnabled());

		worldInfo.setTerrainType(WorldType.LARGE_BIOMES);
        this.largeBiomesProvider = new ChunkProviderGenerate(world, seed, world.getWorldInfo().isMapFeaturesEnabled());

		worldInfo.setTerrainType(WorldType.DEFAULT_1_1);
        this.DEFAULT_1_1 = new ChunkProviderGenerate(world, seed, world.getWorldInfo().isMapFeaturesEnabled());
		
		// TODO flat worldtype
        this.flat = new ChunkProviderFlat(world, seed, world.getWorldInfo().isMapFeaturesEnabled(), "");
		//world.provider.worldChunkMgr = FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(-1).getWorldChunkManager();
		//world.provider.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.hell, 0.0F);
        this.hell = new ChunkProviderHell(world, seed);
		//world.provider.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.sky, 0.0F);
        this.end = new ChunkProviderEnd(world, seed);

		worldInfo.setTerrainType(worldTypeTmp);

		try {
			//generatorOptionsField = world.getWorldInfo().getClass().getDeclaredField("generatorOptions");
			//generatorOptionsField = ObfuscationReflectionHelper.findField(world.getWorldInfo().getClass(), "field_76100_a");
			//Class<? extends WorldInfo> worldInfoClass = world.getWorldInfo().getClass(); // this works
			generatorOptionsField = ReflectionHelper.findField(world.getWorldInfo().getClass(), "generatorOptions");
			//generatorOptionsField = ObfuscationReflectionHelper.findField
			generatorOptionsField.setAccessible(true);
			//generatorOptionsMeth = MethodHandles.lookup().unreflectSetter(generatorOptionsField.setAccessible);
		} catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public Chunk provideChunk(int x, int z) {
		//String worldTypeStr = decideWorldGenerator(x, z);
		//WorldType worldType = WorldType.parseWorldType(worldTypeStr);

		return getTargetChunkProvider(x, z).provideChunk(x, z);

		//if(x>0)
        //    return normal.provideChunk(x, z);
        //else
        //    return hell.provideChunk(x, z);

		// it is much nicer to just call the IChunkProvider by hand. However
		// for that I'd need to add a compile-time dependency on every mod
		// that adds a world generator. This seems to be the only 'dynamic' way.

		/*
		if(!worldDict.containsKey(worldTypeStr)) {
        	//WorldInfo worldInfo = new WorldInfo(new WorldSettings(this.seed, WorldSettings.GameType.SURVIVAL, this.mapFeaturesEnabled, false, worldType), "GeneratorSplicer_" + worldTypeStr);
			WorldSettings worldSettings = new WorldSettings(this.seed, WorldSettings.GameType.SURVIVAL, this.mapFeaturesEnabled, false, worldType);
			//ISaveHandler saveHandler = new SaveHandler(new File("./gays"), "GeneratorSplicer_" + worldTypeStr, false);
			// MinecraftServer, ISaveHandler, String world-name, int dimension-id, WorldSettings, Profiler
			worldDict.put(worldTypeStr, new WorldServer(FMLCommonHandler.instance().getMinecraftServerInstance(), this.world.getSaveHandler(), "GeneratorSplicer_" + worldTypeStr, -58008, worldSettings, null));
		}
		*/

		//return worldDict.get(worldTypeStr).getChunkProvider().provideChunk(x, z);

		//Class<? extends IChunkProvider> chunkProviderClass = worldType.chunkProviderClass;

		//IChunkProvider worldProvider = worldType.getChunkGenerator(this.world, "");
		//return worldProvider.provideChunk(x, z);

		/*
		IChunkProvider obj = new IChunkProvider();

        // Check if any method has a boolean parameter
        boolean hasBooleanParameter = false;

        // Get all methods of the class
        Method[] methods = IChunkProvider.class.getDeclaredMethods();

        // Iterate through methods and check parameters
        for (Method method : methods) {
            if (method.getName().equals("myMethod")) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                // Check if parameter types include boolean
                for (Class<?> paramType : parameterTypes) {
                    if (paramType == boolean.class) {
                        hasBooleanParameter = true;
                        break;  // No need to check further
                    }
                }
            }
            if (hasBooleanParameter) {
                break;  // No need to check further
            }
        }

		if(hasBooleanParameter)
			return null;
		else
			return null;

		// mm
        // Access the chunk provider class
        Class<? extends WorldType> chunkProviderClass = worldType;

        // Instantiate the chunk provider (example with actual constructor parameters and arguments)
        IChunkProvider chunkProvider = null;
        try {
            // Example constructor parameters and arguments for ChunkProviderGenerate
            chunkProvider = chunkProviderClass.getConstructor(net.minecraft.world.World.class, long.class, boolean.class)
                    .newInstance(this.world, this.seed, this.mapFeaturesEnabled);
        } catch (Exception e) {
            e.printStackTrace();
        }
		*/
    }

	public String decideWorldGenerator(int x, int z) {
		if(x>0)
            return "OWG+INFDEV#0";
            //return normal.provideChunk(x, z);
        else
            //return hell.provideChunk(x, z);
            //return "BIOMESOP";
            //return "RTG";
            //return "ATG";
            //return "OWG";
            //return "OWG+INFDEV#0";
            return "OWG+INFDEV#1";
	}

	public IChunkProvider getTargetChunkProvider(int x, int z) {
		//String worldTypeStr = decideWorldGenerator(x, z);
        //WorldType worldType = WorldType.parseWorldType(worldTypeStr);
		//return worldType.getChunkGenerator(this.world, "");
		// Should I set the worldType to the target worldtype like in the switch statement above? Might not need patched BoP then
		world.getWorldInfo().setTerrainType(worldTypeTmp);
		//generateStrTmp = world.getWorldInfo().getGeneratorOptions(); all generators have an explicit options field so i dont think this is needed

		String str = decideWorldGenerator(x, z);
		String str2 = "";
		if (str.contains("+")) {
			str2 = str.substring(str.indexOf('+') + 1);
			str = str.substring(0, str.indexOf('+'));
		}

		if (world.getWorldInfo().getGeneratorOptions() != str2) {
			System.out.println(world.getWorldInfo().getGeneratorOptions());
			setGeneratorOptions(world, str2);
			System.out.println(world.getWorldInfo().getGeneratorOptions());
		}
		
		// this is it
		//return WorldType.parseWorldType(decideWorldGenerator(x, z)).getChunkGenerator(this.world, "");

		switch (str) {
			case "DEFAULT":
				world.provider.worldChunkMgr = this.worldChunkMgr;
				return normal;
			case "AMPLIFIED":
				world.provider.worldChunkMgr = this.worldChunkMgr;
				return amplifiedProvider; 
			case "LARGE_BIOMES":
				world.provider.worldChunkMgr = new WorldChunkManager(this.seed, WorldType.LARGE_BIOMES);
				return largeBiomesProvider;
			case "FLAT":
				world.provider.worldChunkMgr = new WorldChunkManager(this.seed, WorldType.FLAT);
				return flat;
			case "HELL":
				world.provider.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.hell, 0.0F);
            	return hell;
			case "NETHER":
				world.provider.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.hell, 0.0F);
            	return hell;
			case "END":
				world.provider.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.sky, 0.0F);;
				return end;
			case "DEFAULT_1_1":
				world.provider.worldChunkMgr = new WorldChunkManager(this.seed, WorldType.DEFAULT_1_1);
				return DEFAULT_1_1;
			default:
				//System.out.println("here be dragsons");
				break;
		}

		//String worldTypeStr = decideWorldGenerator(x, z);
        //WorldType worldType = WorldType.parseWorldType(worldTypeStr);
        //return worldType.getChunkGenerator(this.world, "");

        // this is it
		//world.provider.worldChunkMgr = WorldProvider.getProviderForDimension(this.world.getWorldInfo().getVanillaDimension()).worldChunkMgr;
		//System.out.println("VERY VERY misbehaving worldprovider: " + world.provider.worldChunkMgr);
		//System.out.println("VERY VERY misbehaving worldprovider33: " + WorldProvider.getProviderForDimension(this.world.getWorldInfo().getVanillaDimension()));
		//System.out.println("VERY VERY misbehaving worldprovider66: " + WorldType.parseWorldType(worldTypeStr).getChunkManager(world));
		//world.provider.worldChunkMgr = WorldType.parseWorldType(worldTypeStr).getChunkManager(world);

		if (WorldType.parseWorldType(str) != null) {
			world.provider.worldChunkMgr = WorldType.parseWorldType(str).getChunkManager(world);
			//worldInfo.setTerrainType(WorldType.AMPLIFIED);
        	return WorldType.parseWorldType(str).getChunkGenerator(this.world, str2);
		}

		return null;
	}

	public static void setGeneratorOptions(World world, String str2) {
		try {
			System.out.println(world);
			System.out.println(str2);
			//generatorOptionsField.set(world.getWorldInfo(), str2);
			//generatorOptionsMeth.invoke(generatorOptions, str2);}
			ObfuscationReflectionHelper.setPrivateValue(WorldInfo.class, world.getWorldInfo(), str2, "field_82576_c", "generatorOptions");
			//ObfuscationReflectionHelper.setPrivateValue(generatorOptionsField, world.getWorldInfo(), str2, "generatorOptions");
		} catch (Exception e) {	e.printStackTrace();}
	}

    @Override
    public boolean chunkExists(int x, int z) {
        // Check if any of the providers has the chunk
        //return amplifiedProvider.chunkExists(x, z) || largeBiomesProvider.chunkExists(x, z);
		return true;//this is what chunkproviderfarlands does /shrug
    }

    @Override
	public void populate(IChunkProvider p7, int x, int z) {
		//this.populate(x, z);
		getTargetChunkProvider(x, z).populate(p7, x, z);
	}

	/*
    public void populate(int x, int z) {
        // Randomly select which chunk provider to use for population
        //IChunkProvider selectedProvider = getRandomProvider();

        // Delegate population to the selected provider
		//if(x>0)
        //	normal.populate(normal, x, z);
		//else
        //	hell.populate(normal, x, z);
		getTargetChunkProvider(x, z).populate(x, z);
    }
	*/

	/*
    private IChunkProvider getProvider(int x, int z) {
        if(x>0)
			return hell;
		else
			return normal;
    }
	*/

	public void saveExtraData() {}

	public void recreateStructures(int x,int z) {
		//todo
		getTargetChunkProvider(x, z).recreateStructures(x, z);
	}

	public int getLoadedChunkCount() {
		return 0;
	}

	// stronghold locate
	public ChunkPosition func_147416_a(World worldIn, String idk, int a, int b, int c) {
		//todo
		return null;
	}

	public List getPossibleCreatures(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_) {
		//todo 
		//return normal.getPossibleCreatures(p_73155_1_, p_73155_2_, p_73155_3_, p_73155_4_);
		return getTargetChunkProvider(p_73155_2_, p_73155_4_).getPossibleCreatures(p_73155_1_, p_73155_2_, p_73155_3_, p_73155_4_);
        //BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(p_73155_2_, p_73155_4_);
        //return p_73155_1_ == EnumCreatureType.monster && this.scatteredFeatureGenerator.func_143030_a(p_73155_2_, p_73155_3_, p_73155_4_) ? this.scatteredFeatureGenerator.getScatteredFeatureSpawnList() : biomegenbase.getSpawnableList(p_73155_1_);
    }

	public String makeString() {
        return "RandomLevelSource";
    }

	public boolean canSave() {
        return true;
    }

	public boolean unloadQueuedChunks() {
        return false;
    }

	public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
        return true;
    }

	public Chunk loadChunk(int x, int z) {
        return this.provideChunk(x, z);
    }

    // Implement other methods of IChunkProvider as needed

    // ...
	/*
	try {
            // Step 1: Get the Class object for DimensionManager
            Class<?> dimensionManagerClass = Class.forName("net.minecraftforge.common.DimensionManager");

            // Step 2: Get the Field object for the private static field 'providers'
            Field providersField = dimensionManagerClass.getDeclaredField("providers");

            // Step 3: Override access control to make the field accessible
            providersField.setAccessible(true);

            // Step 4: Read the value of the providers field (which is a Hashtable<Integer, Class<? extends WorldProvider>>)
            @SuppressWarnings("unchecked")
            Hashtable<Integer, Class<? extends WorldProvider>> providers =
                    (Hashtable<Integer, Class<? extends WorldProvider>>) providersField.get(null);

            // Now you can work with the 'providers' Hashtable
            // Example: Iterate through the Hashtable and print its contents
            for (Integer key : providers.keySet()) {
                Class<? extends WorldProvider> providerClass = providers.get(key);
                System.out.println("Key: " + key + ", Value: " + providerClass.getName());
            }

        } catch (ClassNotFoundException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
	*/

}
