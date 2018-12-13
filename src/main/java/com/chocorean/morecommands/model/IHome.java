package com.chocorean.morecommands.model;

import net.minecraft.util.math.BlockPos;

public interface IHome {
    public String getUsername();
    public BlockPos getPosition();
    public float getYaw();
    public float getPitch();
}
