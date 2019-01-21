package com.chocorean.morecommands.command;

import com.chocorean.morecommands.MoreCommands;
import com.chocorean.morecommands.exception.InvalidNumberOfArgumentsException;
import com.chocorean.morecommands.exception.MoreCommandsException;
import com.chocorean.morecommands.exception.WarpNotFoundException;
import com.chocorean.morecommands.storage.StorageModule;
import com.chocorean.morecommands.storage.datasource.IDataSourceStrategy;
import com.mojang.authlib.GameProfile;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListOps;

import java.sql.SQLException;

public class DelWarpCommand extends CommandBase {
    private final StorageModule storage;

    public DelWarpCommand(IDataSourceStrategy strategy) {
        storage = new StorageModule(strategy);
    }

    @Override
    public String getName() {
        return "delwarp";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return MoreCommands.getConfig().getDelwarpUsage();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 1) {
            throw new InvalidNumberOfArgumentsException();
        }
        boolean doesWarpExist = false;
        try {
            for (String warpName : this.storage.listWarps()) {
                if (args[0].equals(warpName)) {
                    doesWarpExist = true;
                    break;
                }
            }
        } catch (SQLException e) {
            throw new MoreCommandsException("Something wrong happened. Please contact ops.");
        }
        if (!doesWarpExist) {
            throw new WarpNotFoundException(String.format(MoreCommands.getConfig().getWarpNotFoundMessage(),args[0]));
        } else {

        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        UserListOps ops = server.getPlayerList().getOppedPlayers();
        GameProfile gp = ops.getGameProfileFromName(sender.getName());
        try {
            gp.getName(); // can trigger NPE
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }
}
