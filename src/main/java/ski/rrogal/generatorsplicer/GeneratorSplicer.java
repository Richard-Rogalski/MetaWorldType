package com.kpabr.GeneratorSplicer;

import java.io.IOException;
import java.net.UnknownHostException;

import com.kpabr.GeneratorSplicer.CommonProxy;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.ChunkProviderEnd;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.ChunkProviderHell;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
@Mod(modid = GeneratorSplicer.MODID, version = GeneratorSplicer.VERSION, name = GeneratorSplicer.NAME)
public class GeneratorSplicer
{
	
    @SidedProxy(clientSide="com.kpabr.GeneratorSplicer.client.ClientProxy", serverSide="com.kpabr.GeneratorSplicer.CommonProxy")
    public static CommonProxy proxy;
	
 
    /*Mod ID and Version declarations*/
    public static final String MODID = "generatorsplicer";
    public static final String VERSION = "1.0.0";
    public static final String NAME = "generatorsplicer";

	public static WorldType generatorsplicer;
    
    public static GeneratorSplicer instance;
    
    //public static int threshold;
    //public static int thresholdNether;
    //public static int thresholdEnd;

	//public static String directions;
	//public static boolean genNorth;
	//public static boolean genEast;
	//public static boolean genWest;
	//public static boolean genSouth;

	public static int regionSize;
	public static String worldSpawnGenerator; // TODO this currently needs to be in generatorsArray (even with a weight of 0 works), i should probably add it if it isn't present

	public static String[] generatorsArray;
	public static String[] generatorOptionsArray;
	public static float[] generatorWeightsArray;
    
    public static Configuration config;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	config = new Configuration(event.getSuggestedConfigurationFile()); //gets default config file
    	
    	GeneratorSplicer.config.load();
        //this.threshold = GeneratorSplicer.config.getInt("GeneratorSplicerStart", Configuration.CATEGORY_GENERAL, -1, -1, 12550820, "Approximate Far Lands start distance. Might not change the start distance for some generators added by mods. (set to -1 for default distance)");
        //this.thresholdNether = GeneratorSplicer.config.getInt("GeneratorSplicerStartNether", Configuration.CATEGORY_GENERAL, -1, -1, 12550820, "Approximate Far Lands start distance in the nether. If you want the farlands in the nether to line up with farlands in the overworld, set this to an eighth of the value. (set to -1 for default distance)");
        //this.thresholdEnd = GeneratorSplicer.config.getInt("GeneratorSplicerStartEnd", Configuration.CATEGORY_GENERAL, -1, -1, 12550820, "Approximate Far Lands start distance in the end. (set to -1 for default distance)");
        //this.directions = GeneratorSplicer.config.getString("generateInDirection", Configuration.CATEGORY_GENERAL, "NEWS", "Directions that farlands will generate. Examples: \"NEWS\" for all directions. \"NW\" to spawn them north and to the west. \"E\" to spawn them to the east.");
		//this.genNorth = false; this.genEast = false; this.genWest = false; this.genSouth = false;

		worldSpawnGenerator = this.config.getString("worldSpawnGenerator", Configuration.CATEGORY_GENERAL, "DEFAULT", "The worldType the world spawn will always be. MUST be an overworld worldType, not one from another dimension."); //TODO document how big the worldspawn provider will be
		regionSize = this.config.getInt("regionSize", Configuration.CATEGORY_GENERAL, 128, 1, 12550820, "The length and width size of regions of other worldTypes, in chunks. (a chunk is 16x16 blocks)");

		String[] defaultValues = new String[] {"DEFAULT@0.75", "AMPLIFIED@0.25"}; //TODO public static final this
		String[] strArray = this.config.getStringList("worldTypesArray", Configuration.CATEGORY_GENERAL, defaultValues, "The list of worldTypes to splice into our worldType. Refer to the modpage for details");

		//String str, str2;
		float floatTracker = 0.0f;
		this.generatorsArray = new String[strArray.length];
		this.generatorOptionsArray = new String[strArray.length];
		this.generatorWeightsArray = new float[strArray.length];

		for (int i=0; i < strArray.length; i++) {
			if(strArray[i].contains("+")) {
				generatorsArray[i] = strArray[i].substring(0, strArray[i].indexOf('+'));
				generatorOptionsArray[i] = strArray[i].substring((strArray[i].indexOf('+') + 1), strArray[i].indexOf('@'));
			} else {
				generatorsArray[i] = strArray[i].substring(0, strArray[i].indexOf('@'));
				generatorOptionsArray[i] = "";
			}
			floatTracker += Float.parseFloat(strArray[i].substring(strArray[i].indexOf('@') + 1));
			generatorWeightsArray[i] = floatTracker;
			System.out.println("REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE-1: " + generatorOptionsArray[i]);
			System.out.println("REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE-2: " + generatorsArray[i]);
			System.out.println("REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE-2: " + generatorWeightsArray[i]);
		}

		// TODO print an error if the last entry in the weights array is > 1

		//if(this.directions.contains("N"))
		//	this.genNorth = true;
		//if(this.directions.contains("E"))
        //    this.genEast = true;
		//if(this.directions.contains("W"))
        //    this.genWest = true;
		//if(this.directions.contains("S"))
        //    this.genSouth = true;
        GeneratorSplicer.config.save(); //TODO: make farlands able to start after the default
    }


    @EventHandler
    public void load(FMLInitializationEvent event)
    {
		generatorsplicer = new SplicedWorldType("GeneratorSplicer");
	
        this.instance = this;
        
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.TERRAIN_GEN_BUS.register(this);
        
     	proxy.registerRenderers();//?
    }

	/*
    @SubscribeEvent
    public void onGen(ChunkProviderEvent.InitNoiseField event)
    {
    	//uses static 1 and -1 for dimensions, won't support multiworlds / multiverse, should fix
        if(event.chunkProvider instanceof ChunkProviderEnd)// && Minecraft.getMinecraft().theWorld.provider.terrainType instanceof FarWorldType)
        {
            //ChunkProviderFarEnd end = (ChunkProviderFarEnd)(new ChunkProviderFarEnd(DimensionManager.getWorld(1), DimensionManager.getWorld(1).getSeed()));
            //event.setResult(Result.DENY);

            //event.noisefield = end.initializeNoiseField(null, event.posX, event.posY, event.posZ, event.sizeX, event.sizeY, event.sizeZ);
        }
        else if(event.chunkProvider instanceof ChunkProviderHell)// && Minecraft.getMinecraft().theWorld.provider.terrainType instanceof FarWorldType)
        {
            //ChunkProviderFarNether nether = (ChunkProviderFarNether)(new ChunkProviderFarNether(DimensionManager.getWorld(-1), DimensionManager.getWorld(-1).getSeed()));
            //event.setResult(Result.DENY);

            //event.noisefield = nether.initializeNoiseField(null, event.posX, event.posY, event.posZ, event.sizeX, event.sizeY, event.sizeZ);
        }  
		// should probably just check if it's dimension 0 for now
		//else if(event.chunkProvider instanceof ChunkProviderGenerate){
            //event.noisefield = ((ChunkProviderGenerate)DimensionManager.getProvider(0).createChunkGenerator()).initializeNoiseField(null, event.posX, event.posY, event.posZ, event.sizeX, event.sizeY, event.sizeZ);
		//}
    }
	*/
}

