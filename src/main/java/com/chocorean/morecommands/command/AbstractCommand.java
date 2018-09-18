package com.chocorean.morecommands.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCommand extends CommandBase implements ICommand {
    private final ArrayList<String> aliases;

    AbstractCommand(){
        aliases = new ArrayList<>();
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return this.getRequiredPermissionLevel() == 0;
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) {
        return new ArrayList<>();
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return true;
    }

    @Override
    public int compareTo(ICommand iCommand) {
        return this.getName().compareTo(iCommand.getName());
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
}
