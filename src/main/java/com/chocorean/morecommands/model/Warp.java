package com.chocorean.morecommands.model;

import net.minecraft.util.math.BlockPos;

public class Warp implements IWarp {
    private String name;
    private BlockPos position;
    private int dimension;
    private float yaw;
    private float pitch;

    public Warp(String n, BlockPos p, int d, float y, float pi) {
        name = n;
        position = p;
        dimension = d;
        yaw = y;
        pitch = pi;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public BlockPos getPosition() {
        return position;
    }

    @Override
    public int getDimension() {
        return dimension;
    }

    @Override
    public float getYaw() {
        return yaw;
    }

    @Override
    public float getPitch() {
        return pitch;
    }
}
