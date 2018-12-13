package com.chocorean.morecommands.model;

import net.minecraft.util.math.BlockPos;

public class PlayerPos {
    private BlockPos position;
    private int dimension;
    private float yaw;
    private float pitch;

    public PlayerPos(BlockPos p, int d, float y, float pi) {
        position = p;
        dimension = d;
        yaw = y;
        pitch = pi;
    }

    public int getDimension() {
        return dimension;
    }

    public BlockPos getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }
}
