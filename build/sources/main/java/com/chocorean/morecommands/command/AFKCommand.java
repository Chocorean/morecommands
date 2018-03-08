package com.chocorean.morecommands.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.GameType;

import java.util.ArrayList;

public class AFKCommand extends AbstractCommand {
    public static ArrayList<ICommandSender> joueurs;
    public AFKCommand() {
        super();
        joueurs = new ArrayList<ICommandSender>();
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
            server.getPlayerList().sendChatMsg(new TextComponentString("* " + sender.getName() + " is now AFK"));
        }
    }
}
