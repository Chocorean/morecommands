package com.chocorean.morecommands.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class SetSpawnCommand extends AbstractCommand {
    @Override
    public String getCommandName() {
        return "setspawn";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/setspawn";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (!sender.getEntityWorld().isRemote) {
            sender.getEntityWorld().setSpawnPoint(sender.getPosition());
            ((EntityPlayerMP)sender).addChatComponentMessage(new TextComponentString("Spawnpoint has been changed."));
        }
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 3;
    }
}
