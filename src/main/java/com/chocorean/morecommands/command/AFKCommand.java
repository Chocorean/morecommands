package com.chocorean.morecommands.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

import java.util.ArrayList;

public class AFKCommand extends AbstractCommand {
    public static ArrayList<EntityPlayer> players;
    public AFKCommand() {
        super();
        players = new ArrayList<EntityPlayer>();
    }

    @Override
    public String getCommandName() {
        return "afk";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/afk";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (!server.getEntityWorld().isRemote){
            players.add((EntityPlayer) sender);
            server.getPlayerList().sendChatMsg(new TextComponentString("* " + sender.getName() + " is now AFK"));
        }
    }
}
