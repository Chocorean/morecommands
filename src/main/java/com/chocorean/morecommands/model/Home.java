package com.chocorean.morecommands.model;

import net.minecraft.util.math.BlockPos;

public class Home implements IHome {
    private String username;
    private BlockPos position;
    private float yaw;
    private float pitch;

    public Home(String u, BlockPos p, float y, float pi) {
        username = u;
        position = p;
        yaw = y;
        pitch = pi;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public BlockPos getPosition() {
        return position;
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
