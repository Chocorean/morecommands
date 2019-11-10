package com.chocorean.morecommands.model;

import net.minecraft.util.math.BlockPos;

public interface IWarp {
    public String getName();
    public BlockPos getPosition();
    public int getDimension();
    public float getYaw();
    public float getPitch();
}
