package com.chocorean.morecommands.command;

import com.chocorean.morecommands.MoreCommands;
import com.chocorean.morecommands.model.PlayerPos;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class SpawnCommand extends CommandBase {
    @Override
    public String getName() {
        return "spawn";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return MoreCommands.getConfig().getSpawnUsage();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        EntityPlayerMP p = (EntityPlayerMP)sender;
        PlayerPos newLastPos = new PlayerPos(p.getPosition(), p.dimension, p.rotationYaw, p.rotationPitch);
        BackCommand.backList.put(sender.getName(), newLastPos);
        BlockPos spawn = server.getEntityWorld().getSpawnPoint();
        if (p.dimension != 0) p.changeDimension(0);
        p.connection.setPlayerLocation(spawn.getX(), spawn.getY(), spawn.getZ(), p.rotationYaw, p.rotationPitch);
        p.connection.sendPacket(new SPacketChat(new TextComponentString(MoreCommands.getConfig().getOnSpawnMessage())));
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }
}
