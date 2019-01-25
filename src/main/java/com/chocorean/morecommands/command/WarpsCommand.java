package com.chocorean.morecommands.command;

import com.chocorean.morecommands.MoreCommands;
import com.chocorean.morecommands.model.IWarp;
import com.chocorean.morecommands.storage.IStorageStrategy;
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
import java.util.ArrayList;

public class WarpsCommand extends CommandBase {
    private final IStorageStrategy storage;

    public WarpsCommand(IDataSourceStrategy strategy) {
        storage = new StorageModule(strategy);
    }

    @Override
    public String getName() {
        return "warps";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return MoreCommands.getConfig().getUsageConfig().getWarpsUsage();
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        StringBuffer buffer = new StringBuffer("Available warps: ");
        try {
            ArrayList<String> warps = storage.listWarps();
            for (int i=0; i<warps.size(); i++) {
                buffer.append(warps.get(i));
                if (i != warps.size()-1) buffer.append(", ");
            }
            ((EntityPlayerMP)sender).connection.sendPacket(new SPacketChat(new TextComponentString(buffer.toString())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }
}
