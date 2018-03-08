package com.chocorean.morecommands.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class SpawnCommand extends AbstractCommand {
    @Override
    public String getCommandName() {
        return "spawn";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/spawn";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (!sender.getEntityWorld().isRemote){
            ((EntityPlayerMP)sender).addChatComponentMessage(new TextComponentString("Teleporting to spawn..."));
            BlockPos spawn = server.getEntityWorld().getSpawnPoint();
            ((EntityPlayerMP)sender).connection.setPlayerLocation(spawn.getX(),spawn.getY(),spawn.getZ(),0, 0);
        }
    }
}
