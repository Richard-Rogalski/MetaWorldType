package com.kpabr.GeneratorSplicer;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.*;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.BlockDoor;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class SplicedWorldData extends WorldSavedData {

    private static final String DATA_NAME = "generatorsplicer_world_data";
    private int counter = 0;
	//private byte[][] spliceMap = new byte[30000000 >> 4][30000000 >> 4]
	private Map<Point, Byte> splicedMap = new HashMap<>();

    public SplicedWorldData() {
        super(DATA_NAME);
    }

    public SplicedWorldData(String name) {
        super(name);
    }

    public static SplicedWorldData get(World world) {
        // The MapStorage keeps track of world data instances
        MapStorage storage = world.perWorldStorage;
        SplicedWorldData instance = (SplicedWorldData) storage.loadData(SplicedWorldData.class, DATA_NAME);

        // If the instance doesn't exist, create a new one
        if (instance == null) {
            instance = new SplicedWorldData();
            storage.setData(DATA_NAME, instance);
        }

        return instance;
    }

    public int getCounter() {
        return counter;
    }

    public void incrementCounter() {
        counter++;
        markDirty(); // Mark the data as dirty so it will be saved
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        // Load data from NBT
        counter = nbt.getInteger("Counter");
    }
/*
    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        // Save data to NBT
        //nbt.setInteger("Counter", counter);
        //return nbt;

		NBTTagList tagList = new NBTTagList();

        for (splicedMap.Entry<Point, Byte> entry : map.entrySet()) {
            NBTTagCompound entryTag = new NBTTagCompound();
            entryTag.setString("Key", entry.getKey());
            entryTag.setInteger("Value", entry.getValue());
            tagList.appendTag(entryTag);
        }

        compound.setTag("MapData", tagList);
    }
*/
	@Override
    public void writeToNBT(NBTTagCompound nbt) {}
}

