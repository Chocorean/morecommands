package com.chocorean.morecommands.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class VanishCommand extends AbstractCommand {
    @Override
    public String getName() {
        return "vanish";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/vanish";
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 3;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayerMP player = ((EntityPlayerMP)sender);
        if (player.isInvisible()) {
            player.connection.sendPacket(new SPacketChat(new TextComponentString("You're now visible.")));
            player.setInvisible(false);
        } else {
            player.connection.sendPacket(new SPacketChat(new TextComponentString("You're now invisible.")));
            player.setInvisible(true);
        }
    }
}
