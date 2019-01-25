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
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.UserListOps;
import net.minecraft.util.text.TextComponentString;

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
        return MoreCommands.getConfig().getUsageConfig().getDelwarpUsage();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 1) {
            throw new InvalidNumberOfArgumentsException();
        }
        try {
            if (!this.storage.listWarps().contains(args[0])) {
                throw new WarpNotFoundException(String.format(MoreCommands.getConfig().getMessageConfig().getWarpNotFoundMessage(),args[0]));
            } else {
                this.storage.deleteWarp(args[0]);
                ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString(String.format(MoreCommands.getConfig().getMessageConfig().getOnWarpDeletionMessage(), args[0]))));
            }
        } catch (SQLException e) {
            throw new WarpNotFoundException(args[0]);
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
