package com.chocorean.morecommands.command;

import com.chocorean.morecommands.MoreCommands;
import com.chocorean.morecommands.exception.InvalidNumberOfArgumentsException;
import com.chocorean.morecommands.model.Warp;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import java.sql.SQLException;

public class SetWarpCommand extends CommandBase {
    private final StorageModule storage;

    public SetWarpCommand(IDataSourceStrategy strategy) {
        storage = new StorageModule(strategy);
    }

    @Override
    public String getName() {
        return "setwarp";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.morecommands.setwarp.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 1) {
            throw new InvalidNumberOfArgumentsException();
        }
        EntityPlayerMP p = (EntityPlayerMP)sender;
        int dim = p.dimension;
        BlockPos pos = sender.getPosition();
        Warp warp = new Warp(args[0], pos, dim, p.rotationYaw, p.rotationPitch);
        try {
            this.storage.registerWarp(warp);
            p.connection.sendPacket(new SPacketChat(new TextComponentString(String.format("commands.morecommands.setwarp.success", args[0]))));
        } catch (SQLException e) {
            MoreCommands.LOGGER.error(e);
            p.connection.sendPacket(new SPacketChat(new TextComponentString(String.format("commands.morecommands.setwarp.error", args[0]))));
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
