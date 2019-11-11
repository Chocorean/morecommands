package chocorean.morecommands.model;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;

public class PlayerPos {
    private BlockPos position;
    private int dimension;
    private float yaw;
    private float pitch;

    public PlayerPos(EntityPlayer player) {
        position = player.getPosition();
        dimension = player.dimension;
        yaw = player.rotationYaw;
        pitch = player.rotationPitch;
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
