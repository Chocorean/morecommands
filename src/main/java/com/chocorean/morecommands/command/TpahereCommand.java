package com.chocorean.morecommands.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class TpahereCommand extends AbstractCommand {
    @Override
    public String getCommandName() {
        return "tpahere";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/tpahere <targetPlayer> - Ask the target to teleport to you.";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

    }
}
