package com.chocorean.morecommands.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SetHomeCommand extends AbstractCommand {
    @Override
    public String getCommandName() {
        return "sethome";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/sethome - Define your home";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

    }
}
