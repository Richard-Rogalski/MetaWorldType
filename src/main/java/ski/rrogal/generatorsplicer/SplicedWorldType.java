package com.kpabr.GeneratorSplicer;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
//import net.minecraft.world.gen.ChunkProviderSettings;
//import net.minecraft.world.gen.IChunkGenerator;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.WorldType;

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
import java.util.Random;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.ArrayList;
import java.util.List;

public class SplicedWorldType extends WorldType {

	Random random = new Random();

    public SplicedWorldType(String name) {
        super(name);
    }

    @Override
    public IChunkProvider getChunkGenerator(World world, String generatorOptions) {

		if(true) {
			System.out.println("🤠🤠🤠🤠AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA DEBUG SPLICEDGEN 🤠🤠🤠🤠");
			for(int i = 0; i < WorldType.worldTypes.length; i++) { //16, but can be expanded by forge somehow??
				if(WorldType.worldTypes[i] != null)
					System.out.println("worldtype " + i + ": " + WorldType.worldTypes[i] + " , " + WorldType.worldTypes[i].getWorldTypeID() + " ; " + WorldType.worldTypes[i].getWorldTypeName());
			}
		}

		return new ChunkProviderSpliced(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());

        // Parse the X value from the generatorOptions (you need to implement this part)
		/*
        float xValue = parseXValue(generatorOptions);
		System.out.println("aa" + xValue);

        // Determine which generator to use based on the X value
        if (random.nextInt(2) > 0) {
            // Use Amplified generator when X > 0
            return new ChunkProviderGenerate(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
        } else {
            // Use Large Biomes generator when X < 1
            return new ChunkProviderGenerate(world, 999999, world.getWorldInfo().isMapFeaturesEnabled());
        }
		*/
    }
/*
	public static List<IChunkProvider> getRegisteredGenerators() {
        List<IChunkProvider> generators = new ArrayList<>();

        // Use reflection to access the field containing registered terrain generators
		try {
            // Create an instance of InitMapGenEvent.EventType.CAVE
            InitMapGenEvent.EventType eventType = InitMapGenEvent.EventType.CAVE;

            // Get the modded map generator for the specified event type
            List<IChunkProvider> terrainGenerators = (List<IChunkProvider>) TerrainGen.getModdedMapGen(MapGenBase.cave, eventType);

            generators = terrainGenerators;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return generators;
    }
*/
}
