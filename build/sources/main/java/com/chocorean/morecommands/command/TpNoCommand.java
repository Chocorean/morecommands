package com.chocorean.morecommands.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class TpNoCommand extends AbstractCommand {
    @Override
    public String getCommandName() {
        return "no";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/no - Answer the request by no.";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

    }
}
