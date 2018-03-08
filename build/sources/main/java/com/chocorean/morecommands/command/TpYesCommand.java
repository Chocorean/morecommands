package com.chocorean.morecommands.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class TpYesCommand extends AbstractCommand {
    @Override
    public String getCommandName() {
        return "tpyes";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/tpyes - Answer the request by yes.";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

    }
}
