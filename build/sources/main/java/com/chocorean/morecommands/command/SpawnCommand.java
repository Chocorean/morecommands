package com.chocorean.morecommands.command;

import com.chocorean.morecommands.misc.PosPlayer;
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
            for (PosPlayer pp : BackCommand.players) {
                if (pp.player.getName().equals(sender.getName())){
                    pp.position = sender.getPosition();
                }
            }
            BlockPos spawn = server.getEntityWorld().getSpawnPoint();
            ((EntityPlayerMP)sender).connection.setPlayerLocation(spawn.getX(),spawn.getY(),spawn.getZ(),0, 0);
        }
    }
}
